package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.support.test.InstrumentationRegistry;

import org.junit.*;

import static org.junit.Assert.*;

import java.io.IOException;

public class VendorEntityTest {
    private AppDB           mDb;

    // ********** Constants ********************************************** //
    private static final String     VENDOR_NAME     = "fatsecret.com";

    private static final String     OAUTH1_CONS_KEY     = "5e2322a7bcd84e938b97736a647cb0a5";
    private static final String     OAUTH1_CONS_SEC     = "4e8ff54098ae48639ee5f87925f3a90f";

    private static final Vendor mVendor = new Vendor(VENDOR_NAME);

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = AppDB.getMemoryResident(context);
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }


    // ********** Vendor Constructor & DAO Tests ************************* //
    @Test
    public void testWriteThenReadVendor() {
        assertEquals(Vendor.ID_UNSET, mVendor.getId());

        long newId = mDb.write().toDb(mVendor);
        Vendor v_fromDb = mDb.read().vendorByName(VENDOR_NAME);

        // confirm write-to-database operation also correctly updated id of in-memory object
        assertNotEquals(Vendor.ID_UNSET, mVendor.getId());
        assertEquals(newId, mVendor.getId());

        // confirm the written object and the read object are equal objects with different identity
        assertNotSame(mVendor, v_fromDb);
        assertEquals(mVendor, v_fromDb);
        assertEquals(mVendor.hashCode(), v_fromDb.hashCode());
    }


    // ********** Vendor Collision Tests ********************************* //
    @Test(expected=SQLiteConstraintException.class)
    public void testVendorIdCollision() {
        Vendor v_A = new Vendor(VENDOR_NAME + "_A" );
        long id_A = mDb.write().toDb(v_A);

        Vendor v_B = new Vendor( VENDOR_NAME + "_B");
        v_B.setId(id_A);

        mDb.write().toDb(v_B);

        fail("Expected error not thrown");  // test should fail if exception not thrown
    }

    @Test(expected=SQLiteConstraintException.class)
    public void testVendorNameCollision() {
        Vendor v_A = new Vendor(VENDOR_NAME);
        mDb.write().toDb(v_A);

        Vendor v_B = new Vendor(VENDOR_NAME);
        mDb.write().toDb(v_B);

        fail("Expected error not thrown");  // test should fail if exception not thrown
    }
}
