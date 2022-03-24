package linalg;


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
     * @param blockSize Size of the block to use
     * @return The result of the matrix multiplication of A and B.
     */
    static Matrix blocked(Matrix A, Matrix B) {
        if(!MatrixComparisons.matMultCheck(A, B)) {
            throw new IllegalArgumentException("Number of columns in first matrix must match \n"
                    + "number of rows in second matrix but got " + A.shape() + " and " + B.shape() + ".");
        }

        Matrix product = new Matrix(A.m, B.n);

        int i, j, k, kk, jj;

        // Blocked matrix multiplication
        for(kk=0; kk<A.n; kk+=BLOCK_SIZE) {
            for(jj=0; jj<A.n; jj+=BLOCK_SIZE) {

                // Compute matrix multiplication for the block.
                for(i=0; i<product.m; i++) {
                    for(k=kk; k<kk+BLOCK_SIZE && k<A.n; k++) {
                        for(j=jj; j<jj+BLOCK_SIZE && j<A.n; j++) {
                            product.entries[i][j].re += (A.entries[i][k].re*B.entries[k][j].re - A.entries[i][k].im*B.entries[k][j].im);
                            product.entries[i][j].im += (A.entries[i][k].re*B.entries[k][j].im + A.entries[i][k].im*B.entries[k][j].re);
                        }
                    }
                }
            }
        }

        return product;
    }
}
