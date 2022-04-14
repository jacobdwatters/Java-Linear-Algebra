package linalg;


/**
 * A class providing functionality for LQ factorization.
 */
class LQDecomposition {


    /**
     * Factors a matrix <code>A</code> into a unitary matrix <code>Q</code> and a lower
     * triangular matrix <code>L</code> such that <code>A=LQ</code>.
     *
     * @param A Matrix to factor into QR.
     * @return LQ factorization of the matrix A as an array of matrices of length 2.
     * The first element of the array will be L and the second will be Q.
     */
    protected static Matrix[] LQ(Matrix A) {
        Matrix[] LQ = new Matrix[2];

        Matrix[] QR = QRDecomposition.QR(A.H(), false);
        LQ[0] = QR[1].H();
        LQ[1] = QR[0].H();

        return LQ;
    }


    public static void main(String[] args) {
        double[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String[][] b = {{"3+2i", "-i"},
                        {"i", "3+2.34i"}};

        Matrix M = new Matrix(b);
        Matrix[] LQ = LQ(M);

        Matrix.println("L:\n", LQ[0] + "\n\n");
        Matrix.println("Q:\n", LQ[1] + "\n\n");
        System.out.println(LQ[0].mult(LQ[1]));
    }
}
