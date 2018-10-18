package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB.Credential.*;
import static com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB.Credential.TYPE.*;
import static com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB.Credential.TYPE.OAUTH1_CONSUMER_KEY;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CredentialEntityTest {
    private AppDB           mDb;

    // ********** Constants ********************************************** //
    private static final String     OAUTH1_CONS_KEY     = "5e2322a7bcd84e938b97736a647cb0a5";
    private static final String     OAUTH1_CONS_SEC     = "4e8ff54098ae48639ee5f87925f3a90f";

    private static final Map<TYPE, Credential> mCredentials = new HashMap<TYPE, Credential>();

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = AppDB.getMemoryResident(context);

        Credential c_A = new Credential(OAUTH1_CONSUMER_KEY, OAUTH1_CONS_KEY);
        Credential c_B = new Credential(OAUTH1_CONSUMER_SECRET, OAUTH1_CONS_SEC);
        mCredentials.put(c_A.getType(), c_A);
        mCredentials.put(c_B.getType(), c_B);
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }


    // ********** Credentials Tests ************************************** //
    @Test
    public void testWriteThenReadCredential() {
        Credential c_inMem = mCredentials.get(OAUTH1_CONSUMER_KEY);
        assertEquals(Credential.ID_UNSET, c_inMem.getId());
        long newId = mDb.write().toDb(c_inMem);

        // verify that the write operation set the id correctly in the memory-resident object
        assertEquals(newId, c_inMem.getId());
        assertNotEquals(Credential.ID_UNSET, c_inMem.getId());

        Credential c_fromDb = mDb.read().credentialById(newId);

        assertNotSame(c_inMem, c_fromDb);
        assertEquals(c_inMem, c_fromDb);
    }

    // ********** Credentials Collisions Tests *************************** //
    @Test(expected = SQLiteConstraintException.class)
    public void testCredentialsCollision() {
        Credential c_inMem_A = new Credential(OAUTH1_CONSUMER_KEY, OAUTH1_CONS_KEY);
        Credential c_inMem_B = new Credential(OAUTH1_CONSUMER_KEY, OAUTH1_CONS_KEY);

        mDb.write().toDb(c_inMem_A);
        mDb.write().toDb(c_inMem_B);

        fail("Expected error not thrown");
    }




}
