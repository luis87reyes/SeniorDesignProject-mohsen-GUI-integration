package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Utilities;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Utilities.Hash;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class HashTest {
    @Test
    public void testHashingPrimitives() {
        int A = 1;
        int B = 1;
        int C = 3;

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }

    @Test
    public void testHashingPrimitiveArrays() {
        int[] A = {1, 7, 5, 12};
        int[] B = {1, 7, 5, 12};
        int[] C = {36, 7, 14, 19};

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertNotSame(A, B);
        assertArrayEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }

    @Test
    public void testHashingCollections() {

        Integer[] matchValues   = {256, 37, 90, 111, 2004, 7};
        Integer[] noMatchValues = {1, 2, 3, 4, 5, 6};

        Collection<Integer> A   = new ArrayList<>();
        Collection<Integer> B   = new ArrayList<>();
        Collection<Integer> C   = new ArrayList<>();

        for(Integer value : matchValues) {
            A.add(value);
            B.add(value);
        }

        for(Integer value : noMatchValues) {
            C.add(value);
        }

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertNotSame(A, B);
        assertEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }

    @Test
    public void testHashingObjects() {
        TestObj A = new TestObj(36, 48, 12);
        TestObj B = new TestObj(36, 48, 12);
        TestObj C = new TestObj(72, 96, 108);

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertNotSame(A, B);
        assertEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }

    @Test
    public void testHashingObjectArrays() {
        TestObj[] A = {
                new TestObj(1, 2, 3),
                new TestObj(2, 3, 4),
                new TestObj(3, 4, 5)
        };

        TestObj[] B = {
                new TestObj(1, 2, 3),
                new TestObj(2, 3, 4),
                new TestObj(3, 4, 5)
        };

        TestObj[] C = {
                new TestObj(11, 22, 33),
                new TestObj(22, 33, 44),
                new TestObj(33, 44, 55)
        };

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertNotSame(A, B);
        assertArrayEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }

    @Test
    public void testHashingMultidimensionalBooleanArrays() {
        boolean[][] A = {{false, false, false}, {false, false, true}, {false, true, false}};
        boolean[][] B = {{false, false, false}, {false, false, true}, {false, true, false}};
        boolean[][] C = {{false, true, true}, {true, false, false}, {true, false, true}};

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertNotSame(A, B);
        assertArrayEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }

    @Test
    public void testHashingMultidimensionalCharArrays() {
        char[][] A = {{'A', 'B', 'C'}, {'B', 'C', 'D'}, {'C', 'D', 'E'}};
        char[][] B = {{'A', 'B', 'C'}, {'B', 'C', 'D'}, {'C', 'D', 'E'}};
        char[][] C = {{'D', 'E', 'F'}, {'E', 'F', 'G'}, {'F', 'G', 'H'}};

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertNotSame(A, B);
        assertArrayEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }

    @Test
    public void testHashingMultidimensionalByteArrays() {
        byte[][] A = {{0, 1, 2}, {1, 2, 3}, {2, 3, 4}};
        byte[][] B = {{0, 1, 2}, {1, 2, 3}, {2, 3, 4}};
        byte[][] C = {{4, 5, 6}, {5, 6, 7}, {6, 7, 8}};

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertNotSame(A, B);
        assertArrayEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }

    @Test
    public void testHashingMultidimensionalShortArrays() {
        short[][] A = {{0, 1, 2}, {1, 2, 3}, {2, 3, 4}};
        short[][] B = {{0, 1, 2}, {1, 2, 3}, {2, 3, 4}};
        short[][] C = {{4, 5, 6}, {5, 6, 7}, {6, 7, 8}};

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertNotSame(A, B);
        assertArrayEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }

    @Test
    public void testHashingMultidimensionalIntArrays(){
        // int arrays
        int[][] A = { {1, 2, 3}, {2, 3, 4}, {3, 4, 5}};
        int[][] B = { {1, 2, 3}, {2, 3, 4}, {3, 4, 5}};
        int[][] C = { {11, 22, 33}, {22, 33, 44}, {33, 44, 55}};

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertNotSame(A, B);
        assertArrayEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }

    @Test
    public void testHashingMultidimensionalLongArrays(){
        // int arrays
        long[][] A = { {1, 2, 3}, {2, 3, 4}, {3, 4, 5}};
        long[][] B = { {1, 2, 3}, {2, 3, 4}, {3, 4, 5}};
        long[][] C = { {11, 22, 33}, {22, 33, 44}, {33, 44, 55}};

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertNotSame(A, B);
        assertArrayEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }

    @Test
    public void testHashingMultidimensionalFloatArrays(){
        // int arrays
        float[][] A = { {1.6F, 2.7F, 3.8F}, {2.1F, 3.2F, 4.5F}, {3.4F, 4.5F, 5.6F}};
        float[][] B = { {1.6F, 2.7F, 3.8F}, {2.1F, 3.2F, 4.5F}, {3.4F, 4.5F, 5.6F}};
        float[][] C = { {11.3F, 22.3F, 33.3F}, {22.4F, 33.4F, 44.4F}, {33.8F, 44.8F, 55.8F}};

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertNotSame(A, B);
        assertArrayEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }

    @Test
    public void testHashingMultidimensionalDoubleArrays(){
        // int arrays
        double[][] A = { {1.6, 2.7, 3.8}, {2.1, 3.2, 4.5}, {3.4, 4.5, 5.6}};
        double[][] B = { {1.6, 2.7, 3.8}, {2.1, 3.2, 4.5}, {3.4, 4.5, 5.6}};
        double[][] C = { {11.3, 22.3, 33.3}, {22.4, 33.4, 44.4}, {33.8, 44.8, 55.8}};

        int A_hash = new Hash().hashCycle(A).getInt();
        int B_hash = new Hash().hashCycle(B).getInt();
        int C_hash = new Hash().hashCycle(C).getInt();

        int AA_hash = new Hash().hashCycle(A).hashCycle(A).getInt();
        int AB_hash = new Hash().hashCycle(A).hashCycle(B).getInt();

        assertNotSame(A, B);
        assertArrayEquals(A, B);
        assertNotEquals(A, C);
        assertEquals(A_hash, B_hash);
        assertEquals(AA_hash, AB_hash);
        assertNotEquals(A_hash, C_hash);
        assertNotEquals(A_hash, AA_hash);
    }


   // ********** Inner Classes ****************************************** //
    private class TestObj {
        private int mA;
        private int mB;
        private int mC;

        public TestObj(int a, int b, int c) {
            mA = a;
            mB = b;
            mC = c;
        }

        public void setA(int a) {
            mA = a;
        }

        public void setB(int b) {
            mB = b;
        }

        public void setC(int c) {
            mC = c;
        }

        @Override
        public boolean equals(Object that) {
            if(that == null ) return false;
            if(this == that) return true;
            if(this.getClass() != that.getClass()) return false;

            TestObj thatTestObj = (TestObj) that;

            if(
                    this.mA != thatTestObj.mA ||
                    this.mB != thatTestObj.mB ||
                    this.mC != thatTestObj.mC
            ) return false;
            else return true;
        }

        @Override
        public int hashCode() {
            return new Hash()
                    .hashCycle(mA)
                    .hashCycle(mB)
                    .hashCycle(mC)
                    .getInt();
        }
    }
}
