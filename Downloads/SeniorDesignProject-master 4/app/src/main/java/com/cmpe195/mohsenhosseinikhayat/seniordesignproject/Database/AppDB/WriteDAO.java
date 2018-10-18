package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.support.annotation.NonNull;

import java.util.List;

@Dao
public abstract class WriteDAO {

    // ********** Vendors ************************************************ //
    @Insert
    protected abstract long _toDb(Vendor vendor);
    public long toDb(@NonNull Vendor vendor) {         ///< Wrapper function updates the in-memory representation
        long newId = _toDb(vendor);
        vendor.setId(newId);

        return newId;
    }

    @Insert
    public abstract long[] vendors(@NonNull List<Vendor> vendors);


   // ********** Credentials ******************************************** //
    @Insert
    protected abstract long _toDb(@NonNull Credential credential);
    public long toDb (@NonNull Credential credential) {
        long newId = _toDb(credential);
        credential.setId(newId);

        return newId;
    }


    // ********** Endpoints ********************************************** //
    @Insert
    protected abstract long attachCredential(@NonNull Endpoint.JoinCredential ejc);

    @Insert
    protected abstract long _toDb(@NonNull Endpoint.Spec ep_s);
    public long toDb(@NonNull Endpoint.Spec ep_s) {
        ep_s.setId(_toDb(ep_s));
        return ep_s.getId();
    }

    /**
     *
     * @param endpoint
     * @return
     *
     * @pre     in the case where endpoint does not carry a reference to an instance of Vendor, but
     *          instead just a vendor id (int) the vendor id should be verified to exist prior to being
     *          passed to this function or the write to DB will fail to pass Foreign Key constraint check
     */
    @Transaction
    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    public long toDb(@NonNull Endpoint endpoint) {
        // write vendor to DB if not already written
        Vendor v = endpoint.getVendor();

        if( (v != null) && ( !v.isStored()) ) { toDb(v); }
        if(v == null) { endpoint.setVendor( findVendorById(endpoint.getVendorId()) ); }

        long newId = toDb(
                new Endpoint.Spec(
                        endpoint.getUri(),
                        endpoint.getPath(),
                        endpoint.getMethod(),
                        endpoint.getVendorId()
                ));
        endpoint.setId(newId);


        // write each credential to DB if not already written and add a field to the join table
        List<Credential> creds = endpoint.getCredentials();
        if(creds != null) {
            for (Credential c : creds) {
                if( ! c.isStored() ) { toDb(c); }
                attachCredential( new Endpoint.JoinCredential (
                        c.getId(),
                        endpoint.getId()
                ));
            }
        }
        return newId;
    }

    @Query("SELECT * FROM vendors WHERE id = :vendorId")
    protected abstract Vendor findVendorById(long vendorId);
}
