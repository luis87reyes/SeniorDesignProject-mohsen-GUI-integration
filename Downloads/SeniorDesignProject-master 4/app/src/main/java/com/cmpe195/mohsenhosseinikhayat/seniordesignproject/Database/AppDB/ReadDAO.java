package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public abstract class ReadDAO {

    @Query("SELECT * FROM vendors WHERE id = :id")
    public abstract Vendor vendorById(long id);

    @Query("SELECT * FROM vendors WHERE name = :name")
    public abstract Vendor vendorByName(String name);

    @Query("SELECT * FROM endpoints WHERE id = :endpointId")
    public Endpoint endpointById(long endpointId) {
        Endpoint.Spec ep_s = readSpec(endpointId);
        Endpoint ep = new Endpoint(
                vendorById(ep_s.getVendorId()),
                ep_s,
                endpointCredentials(endpointId)
        );
        ep.setId(endpointId);

        return ep;
    }

    @Query("SELECT * FROM endpoints WHERE id = :endpointId")
    public abstract Endpoint.Spec readSpec(long endpointId);

    @Query("SELECT c.id, c.type, c.value " +
            "FROM endpoint_uses_credential AS euc " +
            "INNER JOIN credentials AS c ON c.id = euc.credential_id " +
            "WHERE euc.endpoint_id = :endpointId")
    public abstract List<Credential> endpointCredentials(long endpointId);

    @Query("SELECT c.id, c.type, c.value " +
            "FROM endpoint_uses_credential AS euc " +
            "INNER JOIN credentials AS c " +
            "WHERE euc.endpoint_id = :endpointId AND c.type = :type")
    public abstract Credential endpointCredentialByType(long endpointId, Credential.TYPE type);

    @Query("SELECT * FROM credentials WHERE id = :credentialId")
    public abstract Credential credentialById(long credentialId);
}
