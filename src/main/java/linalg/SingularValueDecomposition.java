package linalg;

class SingularValueDecomposition {

	private SingularValueDecomposition() { // hide public constructor
		throw new IllegalStateException("Utility class");
	}


	/**
	 * Computes the singular value decomposition of an m-by n matrix.
	 * <br><br>
	 * Warning, the way this method is currently implemented is slow and numerically unstable. 
	 * For a matrix with a large condition number, digits of accuracy may be lost.
	 * 
	 * @param A An m-by-n matrix to compute singular value decomposition of.
	 * @return The singular value decomposition 
	 */
	protected static Matrix[] svd(Matrix A) {
		Matrix B = A.H().sudoDirectSum(A);
		Matrix one, two, three;
		Matrix[] schur = Decompose.schur(B);

		Matrix.println(B.hessu());

		System.out.println("B:\n" + B + "\n");
		Matrix.println("U:\n" + schur[0].round(10) + "\n");
		Matrix.println("T:\n" + schur[1].round(10) + "\n");
		Matrix.println(schur[0].mult(schur[1]).mult(schur[2]).round(10));

		one = schur[0].getSlice(2, 6, 2, 6);
		two = schur[1].getSlice(0, 4, 0, 4);
		three = one.H();


		return null;
	}
	
	

	// TODO: Temp
	public static void main(String[] args) {
		double[][] a = {{-4,   0.8,  2.6, 0.6},
						{-4.5, 1.9, -0.7, 3.3}}; 	// Schur decomposition fails for this  B = [[0, A^T]
													//											[A, 0  ]]
		double[][] b = {{1, 2, 3, 4},
						{2, 1, 5, 6},
						{3, 5, 1, 7},
						{4, 6, 7, 1}};
		
		double[][] c = {{ 3,  3},
						{-3, -3},
						{-1,  1},
						{ 1, -1}};

		double[][] d = {{2}};

		double[][] e = {{0, 2},
						{3, 0}};
		
		Matrix M = new Matrix(c);
		svd(M);
	}
	
}
