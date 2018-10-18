package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.support.test.InstrumentationRegistry;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB.Endpoint.HTTPMethod;

import org.junit.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB.Credential.TYPE.OAUTH1_CONSUMER_KEY;
import static com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB.Credential.TYPE.OAUTH1_CONSUMER_SECRET;
import static org.junit.Assert.*;

public class EndpointEntityTest {
    private AppDB           mDb;

    // ********** Constants ********************************************** //
    private static final String     VENDOR_NAME     = "fatsecret.com";
    private static final String     ENDPOINT_URI    = "http://platform.fatsecret.com/rest/server.api";

    private static final String     ENDPOINT_PATH       = "food.get";
    private static final HTTPMethod ENDPOINT_METHOD     = HTTPMethod.GET;
    private static final String     OAUTH1_CONS_KEY     = "5e2322a7bcd84e938b97736a647cb0a5";
    private static final String     OAUTH1_CONS_SEC     = "4e8ff54098ae48639ee5f87925f3a90f";

    private static final Map<Credential.TYPE, Credential> mCredentials =
            new HashMap<Credential.TYPE, Credential>();

    private static Vendor mVendor;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = AppDB.getMemoryResident(context);

        Credential c_A = new Credential(OAUTH1_CONSUMER_KEY, OAUTH1_CONS_KEY);
        Credential c_B = new Credential(OAUTH1_CONSUMER_SECRET, OAUTH1_CONS_SEC);
        mCredentials.put(c_A.getType(), c_A);
        mCredentials.put(c_B.getType(), c_B);

        mVendor = new Vendor(VENDOR_NAME);
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }


    // ********** Endpoint Tests ***************************************** //
    @Test
    public void testEndpointConstructor_InDbVendorById_NoCredentials() {
        long newVendorId = mDb.write().toDb(mVendor);

        Vendor v = mDb.read().vendorById(newVendorId); // making sure we're using the vendor from the database
        Endpoint endpoint = new Endpoint(newVendorId, ENDPOINT_URI, ENDPOINT_PATH, ENDPOINT_METHOD);
        testEndpointWriteThenRead(endpoint);
    }

    @Test
    public void testEndpointConstructor_InDbVendorInstance_NoCredentials() {
        long newId = mDb.write().toDb(mVendor);

        Endpoint endpoint = new Endpoint(mVendor, ENDPOINT_URI, ENDPOINT_PATH, ENDPOINT_METHOD);
        testEndpointWriteThenRead(endpoint);
    }

    @Test
    public void testEndpointConstructor_InDbVendorById_WithCredentials() {
        mDb.write().toDb(mVendor);
        Endpoint endpoint = new Endpoint(
                mVendor.getId(),
                ENDPOINT_URI, ENDPOINT_PATH, ENDPOINT_METHOD,
                mCredentials.values()
        );
        testEndpointWriteThenRead(endpoint);

        assertEquals(mVendor, endpoint.getVendor());
    }

    @Test
    public void testEndpointConstructor_inMemVendorInstance_WithCredentials() {
        Endpoint endpoint = new Endpoint(
                mVendor,
                ENDPOINT_URI, ENDPOINT_PATH, ENDPOINT_METHOD,
                mCredentials.get(OAUTH1_CONSUMER_KEY),
                mCredentials.get(OAUTH1_CONSUMER_SECRET)
        );
        testEndpointWriteThenRead(endpoint);

        // Test whether in-memory vendor and credentials were successfully added to DB
        Vendor v_result = mDb.read().vendorByName(mVendor.getName());
        Credential c_result_key = mDb.read()
                .endpointCredentialByType(
                        endpoint.getId(),
                        OAUTH1_CONSUMER_KEY
                );
        Credential c_result_secret = mDb.read()
                .endpointCredentialByType(
                        endpoint.getId(),
                        OAUTH1_CONSUMER_SECRET
                );

        assertEquals(v_result, mVendor);
        assertEquals(c_result_key, mCredentials.get(OAUTH1_CONSUMER_KEY));
        assertEquals(c_result_secret, mCredentials.get(OAUTH1_CONSUMER_SECRET));
    }


    @Test(expected = SQLiteConstraintException.class)
    public void testFailOnInvalidVendorId() {
        Endpoint endpoint = new Endpoint(
                1,
                ENDPOINT_URI,
                ENDPOINT_PATH,
                ENDPOINT_METHOD
        );
        mDb.write().toDb(endpoint);

        fail("Expected exception not thrown");
    }


    // ********** Endpoint Collisions ************************************ //
    @Test(expected = SQLiteConstraintException.class)
    public void testEndpointCollision() {
        Endpoint endpoint_A = new Endpoint (
                mVendor,
                ENDPOINT_URI, ENDPOINT_PATH, ENDPOINT_METHOD
        );
        mDb.write().toDb(endpoint_A);

        Endpoint endpoint_B = new Endpoint (
                mVendor,
                ENDPOINT_URI, ENDPOINT_PATH, ENDPOINT_METHOD
        );
        mDb.write().toDb(endpoint_B);

        fail("Expected exception not thrown");
    }

    @Test(expected = SQLiteConstraintException.class)
    public void testEndpointJoinCredentialCollision() {
        Endpoint endpoint = new Endpoint(
                mVendor,
                ENDPOINT_URI, ENDPOINT_PATH, ENDPOINT_METHOD
        );
        mDb.write().toDb(endpoint);

        Credential credential = new Credential(OAUTH1_CONSUMER_KEY, OAUTH1_CONS_KEY);
        mDb.write().toDb(credential);

        Endpoint.JoinCredential A = new Endpoint.JoinCredential(credential.getId(), endpoint.getId());
        mDb.write().attachCredential(A);

        Endpoint.JoinCredential B = new Endpoint.JoinCredential(credential.getId(), endpoint.getId());
        mDb.write().attachCredential(B);

        fail("Expected exception not thrown");
    }


    // ********** Helper Methods ***************************************** //
    private void testEndpointWriteThenRead(Endpoint ep) {
        assertEquals(Endpoint.ID_UNSET, ep.getId());

        long epId = mDb.write().toDb(ep);

        assertNotEquals(Endpoint.ID_UNSET, ep.getId());
        assertEquals(epId, ep.getId());

        Endpoint ep_fromDb = mDb.read().endpointById(epId);

        assertEquals(ep, ep_fromDb);
    }
}
