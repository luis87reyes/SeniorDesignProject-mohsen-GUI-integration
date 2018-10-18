package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB;

import android.arch.persistence.room.*;

@Entity(tableName="credentials",
        indices = {
            @Index(
                    name     = "no_duplicate_records",
                    value   = {"type", "value"},
                    unique  = true
            )
        },
        inheritSuperIndices = true
)
public class Credential extends BaseEntity<Credential> {

    // ********** Member Fields ****************************************** //
    @ColumnInfo(name="type")
    private final Credential.TYPE mType;

    @ColumnInfo(name="value")
    private final String mValue;


    // ********** Constructors ****************************************** //
    public Credential(TYPE type, String value) {
        super();
        this.mType = type;
        this.mValue = value;
    }


    // ********** Getters & Setters ************************************* //
    public TYPE getType() { return mType; }
    public String getValue() { return mValue; }


    // ********** Method Overrides [Object] ****************************** //
    @Override
    public boolean equals(Object that) {
        if(! super.equals(that)) return false;

        Credential thatCredential = (Credential) that;   // cast is safe -- superclass checks for class equality

        if(     // check for any equality-failing conditions
                this.getType()  != thatCredential.getType() ||
                ! this.getValue().equals(thatCredential.getValue())
        ) return false;
        else return true;
    }

    @Override
    public int hashCode() {
        return super.hashCycle()
                .hashCycle(getType())
                .hashCycle(getValue())
                .getInt();
    }


    // ********** Internal Classes *************************************** //
    public enum TYPE {
        OAUTH1_CONSUMER_SECRET,
        OAUTH1_CONSUMER_KEY
    }
}
