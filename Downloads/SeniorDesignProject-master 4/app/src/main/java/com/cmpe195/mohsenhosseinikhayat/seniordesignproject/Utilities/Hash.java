package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Utilities;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

public class Hash {

    // ********** Constants ********************************************** //
    private final String BOOLEAN_ARRAY = "class [Z";
    private final String BYTE_ARRAY = "class [B";
    private final String CHAR_ARRAY = "class [C";
    private final String SHORT_ARRAY = "class [S";
    private final String INT_ARRAY = "class [I";
    private final String LONG_ARRAY = "class [J";
    private final String FLOAT_ARRAY = "class [F";
    private final String DOUBLE_ARRAY = "class [D";

    private final int HASH_BASE = 17;


    // ********** Member Fields ****************************************** //
    private final int mHashValue;


    // ********** Constructors ******************************************* //
    public Hash() {
        mHashValue = HASH_BASE;
    }

    public Hash(int value) {
        mHashValue = value;
    }


    // ********** Getters & Setters ************************************* //
    public int getInt() { return mHashValue; }


    // ********** Type-Specific Process methods ********************* //
    public Hash hashCycle(boolean val) {
        return new Hash(37 * mHashValue + (val ? 0 : 1));
    }
    public Hash hashCycle(byte val) {
        return new Hash(37 * mHashValue + (int) val);
    }
    public Hash hashCycle(char val) {
        return new Hash(37 * mHashValue + (int) val);
    }
    public Hash hashCycle(short val ) {
        return new Hash(37 * mHashValue + (int) val);
    }
    public Hash hashCycle(int val) {
        return new Hash(37 * mHashValue + val);
    }

    public Hash hashCycle(float val) {
        return new Hash(37 * mHashValue + Float.floatToIntBits(val));
    }

    public Hash hashCycle(double val) {
        return new Hash(
                37 * mHashValue + Float.floatToIntBits(
                        37 * mHashValue + Double.doubleToLongBits(val)
                )
        );
    }

    public Hash hashCycle(boolean[] array) {
        Hash mHash = this;

        if(array == null || array.length == 0) return new Hash(37 * mHashValue + 0);
        for(boolean element : array) mHash = mHash.hashCycle(element);

        return mHash;
    }

    public Hash hashCycle(char[] array) {
        Hash mHash = this;

        if(array == null || array.length == 0) return new Hash(37 * mHashValue + 0);
        for(char element : array)
            mHash = mHash.hashCycle(element);

        return mHash;
    }

    public Hash hashCycle(byte[] array) {
        Hash mHash = this;

        if(array == null || array.length == 0) return new Hash(37 * mHashValue + 0);
        for(byte element : array) mHash = mHash.hashCycle(element);

        return mHash;
    }

    public Hash hashCycle(short[] array) {
        Hash mHash = this;

        if(array == null || array.length == 0) return new Hash(37 * mHashValue + 0);
        for(short element : array) mHash = mHash.hashCycle(element);

        return mHash;
    }

    public Hash hashCycle(int[] array) {
        Hash mHash = this;

        if(array == null || array.length == 0) return new Hash(37 * mHashValue + 0);
        for(int element : array) mHash = mHash.hashCycle(element);

        return mHash;
    }

    public Hash hashCycle(long[] array) {
        Hash mHash = this;

        if(array == null || array.length == 0) return new Hash(37 * mHashValue + 0);
        for(long element : array) mHash = mHash.hashCycle(element);

        return mHash;
    }

    public Hash hashCycle(float[] array) {
        Hash mHash = this;

        if(array == null || array.length == 0) return new Hash(37 * mHashValue + 0);
        for(float element : array) mHash = mHash.hashCycle(element);

        return mHash;
    }

    public Hash hashCycle(double[] array) {
        Hash mHash = this;

        if(array == null || array.length == 0) return new Hash(37 * mHashValue + 0);
        for(double element : array) mHash = mHash.hashCycle(element);

        return mHash;
    }

    public Hash hashCycle(Object val) {

        /*
        when hashing multidimensional arrays where the base array type is a primitive,
        the program passes it to the Object-parameter version of the overloaded hashCycle() method.
        Since we cannot Override the hashCode() method on an Array, the default Object method is
        used, which relies on a simple == check, meaning it will fail for two different array
        objects, even if the represented arrays contain identical values. Therefore, to handle
        multi-dimensional arrays, we have to intercept primitive one-dimensional array types and
        redirect them to the appropriate overloaded methods.
        */
        switch(val.getClass().toString()) {
            case BOOLEAN_ARRAY: return this.hashCycle((boolean[]) val);
            case BYTE_ARRAY:    return this.hashCycle((byte[]) val);
            case CHAR_ARRAY:    return this.hashCycle((char[]) val);
            case DOUBLE_ARRAY:  return this.hashCycle((double[]) val);
            case FLOAT_ARRAY:   return this.hashCycle((float[]) val);
            case INT_ARRAY:     return this.hashCycle((int[]) val);
            case LONG_ARRAY:    return this.hashCycle((long[]) val);
            case SHORT_ARRAY:   return this.hashCycle((short[]) val);

            // for all types that are not one-dimensional arrays of primitive types, handle as normal
            default: return new Hash(37 * mHashValue + (val == null ? 0 : val.hashCode()));
        }

    }

    public <T> Hash hashCycle(T[] array) {
        Hash mHash = this;

        if(array == null || array.length == 0) return new Hash(37 * mHashValue + 0);
        for(T element : array) mHash = mHash.hashCycle(element);

        return mHash;
    }

    public <T> Hash hashCycle(Collection<T> collection) {
        Hash mHash = this;

        if(collection == null) return new Hash(37 * mHashValue + 0);
        for(T element : collection) mHash = mHash.hashCycle(element);

        return mHash;
    }
}