package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Database.AppDB;

import android.arch.persistence.room.*;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Database (
        entities = {
                Vendor.class,
                Endpoint.class,
                Endpoint.Spec.class,
                Endpoint.JoinCredential.class,
                Credential.class,
        },
        version = 1,
        exportSchema = false
)
@TypeConverters(AppDB.Converters.class)
public abstract class AppDB extends RoomDatabase {

    private static String DATABASE_NAME = "app_database";
    private static volatile AppDB mDb = null;
    private static volatile AppDB mMemResDb = null;

    public static AppDB get(Context context) {
        return mDb == null ? create(context) : mDb;
    }

    public static AppDB getMemoryResident(Context context) {
        return mMemResDb == null ? createMemRes(context) : mMemResDb;
    }

    private static AppDB create(Context context) {
        return Room.databaseBuilder(
                context,
                AppDB.class,
                DATABASE_NAME
        ).build();
    }

    private static AppDB createMemRes(Context context) {
        return Room.inMemoryDatabaseBuilder(
                context,
                AppDB.class
        ).build();
    }

    // public abstract AppDB.WriteDAO write();
    // public abstract AppDB.ReadDAO  read();
    public abstract WriteDAO write();
    public abstract ReadDAO read();


    // ********** Enum Type Converters *********************************** //
    public abstract static class Converters {
        @TypeConverter
        public static Credential.TYPE toCredentialType(String type)
                throws IllegalArgumentException, NullPointerException {

            return Credential.TYPE.valueOf(type);
        }

        @TypeConverter
        public static String fromCredentialType(Credential.TYPE type) {
            return type.toString();
        }


        @TypeConverter
        public static Endpoint.HTTPMethod toHTTPMethod(String method)
                throws IllegalArgumentException, NullPointerException {

            return Endpoint.HTTPMethod.valueOf(method);
        }

        @TypeConverter
        public static String fromHTTPMethod(Endpoint.HTTPMethod method) {
            return method.toString();
        }


        // ********** Map Type Converters ************************************ //
        @TypeConverter
        public static Map<Credential.TYPE, Credential> fromList(List<Credential> list) {
            HashMap<Credential.TYPE, Credential> map = new HashMap();
            for(Credential c: list) {
                map.put(c.getType(), c);
            }

            return map;
        }

        @TypeConverter
        public static List<Credential> toList(Map<Credential.TYPE, Credential> map) {
            return new ArrayList(map.values());
        }
    }



}
