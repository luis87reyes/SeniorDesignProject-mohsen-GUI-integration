package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Utilities.Hash;

@Entity(indices = {
        @Index(name="unique_id", value="id", unique=true)
})
public abstract class BaseEntity<T> {

    // ********** Member Fields ****************************************** //

    @Ignore
    public static final int ID_UNSET = 0;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;


    // ********** Getters and Setters************************************* //
    protected void  setId(long id)   { mId = id; }
    public long      getId()         { return mId; }


   // ********** Instance Methods *************************************** //
    protected boolean isStored() { return mId != ID_UNSET; }


   // ********** Method Overrides [Object] ****************************** //
    @Override
    public boolean equals(Object that) {
        if(that == null) return false;
        if(that == this) return true;

        // prevents equality of an instance of this class and an instance of any subclass
        // note: redundant as this class is abstract... will consider before removing
        if(that.getClass() != this.getClass()) return false;

        BaseEntity<T> thatEntity = (BaseEntity<T>) that;

        return this.getId() == thatEntity.getId();
    }

    @Override
    public int hashCode() {
        return new Hash().hashCycle(getId()).getInt();
    }

    public Hash hashCycle() {
        return new Hash().hashCycle(getId());
    }
}
