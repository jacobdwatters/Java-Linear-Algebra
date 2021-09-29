package linalg.matrix;

import linalg.Matrix;
import linalg.complex_number.CNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixConstructorTest {
    String expShapeA;
    CNumber[][] expEntriesA;
    int expNumColsA, expNumRowsA;
    Matrix A, B, C;


    @Test // Defines a test method
    void defaultConstructorTestCase() {
        expEntriesA = new CNumber[][]{};
        expShapeA = "0x0";
        expNumColsA = expNumRowsA = 0;

        A = new Matrix();

        assertArrayEquals(expEntriesA, A.getValues());
        assertEquals(expShapeA, A.shape());
        assertEquals(expNumColsA, A.numCols());
        assertEquals(expNumRowsA, A.numRows());
    }


    @Test // Defines a test method
    void sizeConstructorFirstTestCase() {
        expEntriesA = new CNumber[][]{{new CNumber(0)}};
        expShapeA = "1x1";
        expNumColsA = expNumRowsA = 1;

        A = new Matrix(1);

        assertArrayEquals(expEntriesA, A.getValues());
        assertEquals(expShapeA, A.shape());
        assertEquals(expNumColsA, A.numCols());
        assertEquals(expNumRowsA, A.numRows());
    }

    @Test // Defines a test method
    void sizeConstructorSecondTestCase() {
        expEntriesA = new CNumber[][]{{new CNumber(0), new CNumber(0), new CNumber(0)},
                                      {new CNumber(0), new CNumber(0), new CNumber(0)},
                                      {new CNumber(0), new CNumber(0), new CNumber(0)}};
        expShapeA = "3x3";
        expNumColsA = expNumRowsA = 3;

        A = new Matrix(3);

        assertArrayEquals(expEntriesA, A.getValues());
        assertEquals(expShapeA, A.shape());
        assertEquals(expNumColsA, A.numCols());
        assertEquals(expNumRowsA, A.numRows());
    }


    @Test // Defines a test method
    void sizeValueConstructorTestCase() {
        expEntriesA = new CNumber[][]{{new CNumber("3+2i"), new CNumber("3+2i"), new CNumber("3+2i")},
                {new CNumber("3+2i"), new CNumber("3+2i"), new CNumber("3+2i")},
                {new CNumber("3+2i"), new CNumber("3+2i"), new CNumber("3+2i")}};
        expShapeA = "3x3";
        expNumColsA = expNumRowsA = 3;

        A = new Matrix(3, new CNumber("3+2i"));

        assertArrayEquals(expEntriesA, A.getValues());
        assertEquals(expShapeA, A.shape());
        assertEquals(expNumColsA, A.numCols());
        assertEquals(expNumRowsA, A.numRows());
    }


    @Test // Defines a test method
    void rowsColsConstructorTestCase() {
        expEntriesA = new CNumber[][]{{new CNumber(0), new CNumber(0)},
                {new CNumber(0), new CNumber(0)},
                {new CNumber(0), new CNumber(0)}};
        expShapeA = "3x2";
        expNumColsA = 2;
        expNumRowsA = 3;

        A = new Matrix(3, 2);

        assertArrayEquals(expEntriesA, A.getValues());
        assertEquals(expShapeA, A.shape());
        assertEquals(expNumColsA, A.numCols());
        assertEquals(expNumRowsA, A.numRows());
    }


    @Test // Defines a test method
    void rowsColsValueConstructorTestCase() {
        expEntriesA = new CNumber[][]{{new CNumber("3+2i"), new CNumber("3+2i")},
                {new CNumber("3+2i"), new CNumber("3+2i")},
                {new CNumber("3+2i"), new CNumber("3+2i")}};
        expShapeA = "3x2";
        expNumColsA = 2;
        expNumRowsA = 3;

        A = new Matrix(3, 2, new CNumber("3+2i"));

        assertArrayEquals(expEntriesA, A.getValues());
        assertEquals(expShapeA, A.shape());
        assertEquals(expNumColsA, A.numCols());
        assertEquals(expNumRowsA, A.numRows());
    }
}
