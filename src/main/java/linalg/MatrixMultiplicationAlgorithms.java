package linalg;


import linalg.concurrent.ConcurrentMatrixMultiplication;

/**
 * A class containing single-threaded matrix multiplication algorithms.
 */
class MatrixMultiplicationAlgorithms {
    private MatrixMultiplicationAlgorithms() {
        throw new IllegalStateException("Utility class cannot be instanced.");
    }

    /**
     * Size of a block for blocked algorithms.
     */
    final static int BLOCK_SIZE = 100;


    /**
     * Algorithm for multiplying a matrix and a column vector. It is assumes that A and B are the correct size.
     * @param A First matrix to multiply.
     * @param B Second matrix to multiply. This matrix MUST be a column vector.
     * @return The result of matrix-vector multiplication between A and B.
     */
    static Matrix matrixVector(Matrix A, Matrix B) {
        Matrix product = new Matrix(A.m, 1);
        Matrix b = B.T();

        for(int i = 0; i < A.m; i++) {
            for(int j = 0; j < A.n; j++) {
                product.entries[i][0].re += (A.entries[i][j].re*b.entries[0][j].re - A.entries[i][j].im*b.entries[0][j].im);
                product.entries[i][0].im += (A.entries[i][j].re*b.entries[0][j].im + A.entries[i][j].im*b.entries[0][j].re);
            }
        }

        return product;
    }


    /**
     * Standard matrix multiplication algorithm. It is assumed that A and B are appropriate sizes.
     *
     * @param A First matrix to multiply.
     * @param B Second matrix to multiply.
     * @return The result of matrix multiplication between A and B.
     */
    static Matrix standard(Matrix A, Matrix B) {
        Matrix product = new Matrix(A.m, B.n);

        // Note that the j and k loops are swapped to improve cache performance.
        for(int i = 0; i < product.m; i++) {
            for(int k = 0; k < A.n; k++) {
                for(int j = 0; j < product.n; j++) {
                    product.entries[i][j].re += (A.entries[i][k].re * B.entries[k][j].re - A.entries[i][k].im * B.entries[k][j].im);
                    product.entries[i][j].im += (A.entries[i][k].re * B.entries[k][j].im + A.entries[i][k].im * B.entries[k][j].re);
                }
            }
        }

        return product;
    }


    /**
     * A blocked matrix multiplication algorithm.
     *
     * @param A First matrix to multiply.
     * @param B second matrix to multiply with this matrix.
     * @return The result of the matrix multiplication of A and B.
     */
    static Matrix concurrent(Matrix A, Matrix B) {
        ConcurrentMatrixMultiplication multiplier = new ConcurrentMatrixMultiplication(A, B);
        return multiplier.mult();
    }
}
