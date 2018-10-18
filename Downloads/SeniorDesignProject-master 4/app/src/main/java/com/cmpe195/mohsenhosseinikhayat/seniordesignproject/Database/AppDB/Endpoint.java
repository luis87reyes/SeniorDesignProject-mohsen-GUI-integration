package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB;

import android.arch.persistence.room.*;
import android.support.annotation.NonNull;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Utilities.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(inheritSuperIndices=true)
public class Endpoint extends BaseEntity {

    // ********** Member Fields ****************************************** //
    @ColumnInfo(name = "uri")       private final String        mUri;
    @ColumnInfo(name = "path")      private final String        mPath;
    @ColumnInfo(name = "method")    private final HTTPMethod    mMethod;
    @ColumnInfo(name = "vendor_id") private final long          mVendorId;

    @Relation(
            parentColumn = "vendor_id",
            entityColumn = "id"
    )

    @Ignore
    private Vendor mVendor;

    @Ignore
    private final Map<Credential.TYPE, Credential> mCredentials;


    // ********** Constructors ******************************************* //
    public Endpoint(long vendorId, String uri, String path, HTTPMethod method) {
        mCredentials = new HashMap<Credential.TYPE, Credential>();

        mUri = uri;
        mPath = path;
        mMethod = method;
        mVendorId = vendorId;
    }

    public Endpoint(long vendorId, String uri, String path, HTTPMethod method, Credential... credentials) {
        mCredentials = new HashMap<Credential.TYPE, Credential>();

        mUri = uri;
        mPath = path;
        mMethod = method;
        mVendorId = vendorId;
        for(Credential c: credentials) mCredentials.put(c.getType(), c);
    }

    public Endpoint(long vendorId, String uri, String path, HTTPMethod method, Iterable<Credential> credentials) {
        mCredentials = new HashMap<Credential.TYPE, Credential>();

        mUri = uri;
        mPath = path;
        mMethod = method;
        mVendorId = vendorId;
        for(Credential c: credentials) mCredentials.put(c.getType(), c);
    }


    public Endpoint(Vendor vendor, String uri, String path, HTTPMethod method, Credential... credentials) {
        mCredentials = new HashMap<Credential.TYPE, Credential>();

        mUri = uri;
        mPath = path;
        mMethod = method;
        mVendorId = vendor.getId();
        mVendor = vendor;
        for(Credential c: credentials) mCredentials.put(c.getType(), c);
    }

    public Endpoint(Vendor vendor, String uri, String path, HTTPMethod method, Iterable<Credential> credentials) {
        mCredentials = new HashMap<Credential.TYPE, Credential>();

        mUri = uri;
        mPath = path;
        mMethod = method;
        mVendorId = vendor.getId();
        mVendor = vendor;
        for(Credential c: credentials) mCredentials.put(c.getType(), c);
    }

    public Endpoint(Vendor vendor, Endpoint.Spec endpoint, Credential... credentials) {
        mCredentials = new HashMap<Credential.TYPE, Credential>();

        mUri = endpoint.getUri();
        mPath = endpoint.getPath();
        mMethod = endpoint.getMethod();
        mVendorId = vendor.getId();
        mVendor = vendor;
        for(Credential c: credentials) mCredentials.put(c.getType(), c);
    }

    public Endpoint(Vendor vendor, Endpoint.Spec endpoint, Iterable<Credential> credentials) {
        mCredentials = new HashMap<Credential.TYPE, Credential>();

        mUri = endpoint.getUri();
        mPath = endpoint.getPath();
        mMethod = endpoint.getMethod();
        mVendorId = vendor.getId();
        mVendor = vendor;
        for(Credential c: credentials) mCredentials.put(c.getType(), c);
    }


    // ********** Getters & Setters ************************************** //
    public String           getUri()            { return mUri; }
    public String           getPath()           { return mPath; }
    public HTTPMethod       getMethod()         { return mMethod; }
    public long             getVendorId()       { return mVendor == null ? mVendorId : mVendor.getId(); }
    public Vendor           getVendor()         { return mVendor; }
    public List<Credential> getCredentials()    { return new ArrayList(mCredentials.values()); }

    protected void setCredentials(List<Credential> credentials) {
        mCredentials.clear();
        for(Credential c : credentials) mCredentials.put(c.getType(), c);
    }
    protected void setVendor(Vendor vendor) { mVendor = vendor; }

    // ********** Instance Methods *************************************** //
    public Credential getCredentialByType(Credential.TYPE type) { return mCredentials.get(type); }
    public boolean hasCredentialType(Credential.TYPE type) { return mCredentials.containsKey(type); }

    @Override
    public boolean equals(Object that) {
        if(that == null) return false;
        if(! super.equals(that)) return false;
        if(this == that) return false;

        Endpoint thatEndpoint = (Endpoint) that; // superclass method tests getClass() equality

        if (
                ! ( this.getVendorId() == thatEndpoint.getVendorId() )  ||
                ! ( this.getMethod() == thatEndpoint.getMethod() )      ||
                ! ( this.getPath().equals(thatEndpoint.getPath()) )     ||
                ! ( this.getUri().equals(thatEndpoint.getUri()) )       ||
                ! ( this.getCredentials().equals(thatEndpoint.getCredentials())
                        && this.getCredentials() != null || thatEndpoint.getCredentials() != null)
        ) return false;
        else return true;
    }


    @Override
    public int hashCode() {
        return super.hashCycle()
                .hashCycle(getMethod())
                .hashCycle(getPath())
                .hashCycle(getUri())
                .hashCycle(getCredentials())
                .getInt();
    }

    // ********** Inner Classes ****************************************** //
    public enum HTTPMethod {
        GET,
        POST
    }

    @Entity(tableName="endpoints",
            foreignKeys = @ForeignKey(
                    entity = Vendor.class,
                    parentColumns="id",
                    childColumns="vendor_id",
                    onDelete = ForeignKey.SET_NULL,
                    onUpdate = ForeignKey.CASCADE
            ),
            indices = {
                @Index(value = "vendor_id" ),
                @Index( value = {"uri", "path", "http_method"}, unique = true )
            },
            inheritSuperIndices = true
    )
    public static class Spec extends BaseEntity{

        // ********** Member Fields ****************************************** //
        @ColumnInfo(name = "uri")
        private final String mUri;

        @ColumnInfo(name = "path")
        private final String mPath;

        @ColumnInfo(name="http_method")
        private final HTTPMethod mMethod;

        @NonNull
        @ColumnInfo(name="vendor_id")
        private long mVendorId;


        // ********** Constructors ******************************************* //
        public Spec(String uri, String path, HTTPMethod method, @NonNull long vendorId) {
            mUri = uri;
            mPath = path;
            mMethod = method;
            mVendorId = vendorId;
        }


        // ********** Getters & Setters ************************************** //
        @NonNull
        public long         getVendorId()   { return mVendorId; }
        public String       getUri()        { return mUri; }
        public String       getPath()       { return mPath; }
        public HTTPMethod   getMethod()     { return mMethod; }


        // ********** Member Methods ***************************************** //


        // ********** Method Overrides [Object] ****************************** //
        @Override
        public boolean equals(Object that) {
            if(! super.equals(that)) return false;
            Spec thatEndpoint = (Spec) that;        // object is an Spec

            // validate fields
            if(
                    this.getMethod()    != thatEndpoint.getMethod() ||
                    this.getVendorId()  != thatEndpoint.getVendorId() ||
                    ! this.getPath().equals(thatEndpoint.getPath()) ||
                    ! this.getUri().equals(thatEndpoint.getUri())
            ) return false;
            else return true;
        }

        @Override
        public int hashCode() {
            return super.hashCycle()
                    .hashCycle(getVendorId())
                    .hashCycle(getPath())
                    .hashCycle(getUri())
                    .hashCycle(getMethod())
                    .getInt();
        }
    }


    /**
     * \brief Represents a M:N relationship between Endpoint and Credential entities
     */
    @Entity(tableName = "endpoint_uses_credential",
            foreignKeys = {
                @ForeignKey(
                        entity = Spec.class,
                        parentColumns = "id",
                        childColumns = "endpoint_id",
                        onDelete = ForeignKey.CASCADE,      // when deleting an endpoint, delete the association with its credentials
                        onUpdate = ForeignKey.CASCADE       // updating an endpoint's id, make sure it's updated here as well (just a precaution, this should never happen.)
                ),
                @ForeignKey(
                        entity = Credential.class,
                        parentColumns = "id",
                        childColumns = "credential_id",
                        onDelete = ForeignKey.CASCADE,      // when deleting a credential, delete any associations with any endpoints as well
                        onUpdate = ForeignKey.CASCADE       // when updating the id of any credential, make sure the association with any endpoints changes with it (Just a precaution -- this should never happen)
                )
            },
            primaryKeys = {"credential_id", "endpoint_id"},  // composite primary key
            inheritSuperIndices = true
    )
    public static class JoinCredential {
        // ********** Member Fields ****************************************** //
        @ColumnInfo(name = "credential_id", index = true)
        final long mCredentialId;

        @ColumnInfo(name = "endpoint_id", index = true)
        final long mEndpointId;


        // ********** Constructors ******************************************* //
        public JoinCredential(final long credentialId, final long endpointId) {
            mCredentialId = credentialId;
            mEndpointId = endpointId;
        }

        // ********** Getters & Setters ************************************** //
        public long getCredentialId()   { return mCredentialId; }
        public long getEndpointId()     { return mEndpointId; }


        // ********** Overridden Methods [Object] **************************** //
        @Override
        public boolean equals(Object that) {
            if (that == null) return false;
            if (that == this) return true;
            if (this.getClass() != that.getClass()) return false;

            JoinCredential thatEUC = (JoinCredential) that;

            if (
                    this.getCredentialId() != thatEUC.getCredentialId() ||
                            this.getEndpointId() != thatEUC.getEndpointId()
                    ) return false;
            else return true;
        }

        @Override
        public int hashCode() {
            return new Hash()
                    .hashCycle(getCredentialId())
                    .hashCycle(getEndpointId())
                    .getInt();
        }
    }
}
