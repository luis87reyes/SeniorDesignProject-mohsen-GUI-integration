package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB;

import android.arch.persistence.room.*;

@Entity(tableName="vendors",
        indices = {
            @Index(value = "name", unique = true )
        },
        inheritSuperIndices = true
)
public class Vendor extends BaseEntity{

// ********** Instance Fields **************************************** //
    @ColumnInfo(name = "name")
    private String mName;


    // ********** Constructors & Factories ******************************* //
    public Vendor(String name) {
        mName = name;
    }


    // ********** Getters & Setters ************************************** //
    public String getName() { return mName; }
    public void setName(String name) {
        this.mName = name;
    }


    // ********** Method Overrides [Object] ****************************** //
    public boolean equals(Object that) {
        if(!super.equals(that)) return false;
        Vendor thatVendor = (Vendor) that;

        if(
                ! this.getName().equals(thatVendor.getName())
        ) return false;
        else return true;
    }

    @Override
    public int hashCode() {
        return super.hashCycle()
                .hashCycle(getName())
                .getInt();
    }
}
