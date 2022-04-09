package linalg;

import linalg.complex_number.CNumber;

/**
 * A class providing functionality for QR factorization.
 */
class QRDecomposition {
	
	final static int QR_NORMAL = 0;
	final static int QR_COUNT_HOUSEHOLDER = 1;

	private QRDecomposition() { // hide public constructor
		throw new IllegalStateException("Utility class cannot be instantiated.");
	}

	protected static Matrix[] execute(Matrix A, int type) {
		return QR(A, type == QR_COUNT_HOUSEHOLDER);
	}
	
	/**
	 * Factors a matrix <code>A</code> into a unitary matrix <code>Q</code> and an upper
	 * triangular matrix <code>R</code> such that <code>A=QR</code>.
	 * 
	 * @param A Matrix to factor into QR.
	 * @return QR factorization of the matrix A as an array of matrices of length 2. 
	 * The first element of the array will be Q and the second will be R.
	 */
	protected static Matrix[] QR(Matrix A, boolean countHouseholder) {
		Matrix QR[], 
				Q = Matrix.I(A.m), 
				R = A,
				H,
				v, x; // Column Vector
		
		Vector w;
		CNumber norm, two = new CNumber(2);
		CNumber n = CNumber.ZERO;
		int counth = 0; // Counts the number of householder Reflectors used.
		
		for(int j=0; j<A.n; j++) {
			
			if(!R.getSlice(j, A.m, j, j+1).equals(new Matrix(A.m-j, 1))) {

				x = R.getSlice(j, A.m, j, j+1);		
				norm = new CNumber(-x.norm().re);
				
				w = new Vector(A.m-j);
				w.entries[0][0] = CNumber.multiply(CNumber.sign(x.entries[0][0]), norm); // TODO: Exception if m<n
				v = w.sub(x);
				
				if(!v.H().mult(v).entries[0][0].equals(CNumber.ZERO)) {
					n = CNumber.divide(two, v.H().mult(v).entries[0][0]);
				} else {
					n = CNumber.ZERO;
				}
				
				if(!n.equals(CNumber.ZERO)) { // Then we need a reflector.
					counth++;	// For determinant, will need to count number of reflectors used.
					H = Matrix.I(A.m-j).sub(v.mult(v.H()).scalMult(n));
					H = Matrix.I(A.m).setSliceCopy(j, j, H);
					
					R = H.mult(R);
					Q = Q.mult(H);
				}
			}
			
		}
		
		R.roundToZero(14); // Ensure R is triangular by rounding values near zero to zero.
		
		if(countHouseholder) { 
			// Then we also need to return the number of householder reflectors used.
			// This is stored in a 1-by-1 matrix
			QR = new Matrix[3];
			QR[2] = new Matrix(1, 1, counth);
		} else {
			QR = new Matrix[2];
		}
		
		QR[0] = Q;
		QR[1] = R;
		
		return QR;
	}
	
	
	public static void main(String[] args) {
		double[][] a = {{0, 0, 0},
			 	 		{0, 0, -1},
			 	 		{0, 1, 0}}; // TODO: QR decomposition fails for this case
		
		int[][] a1 = { {0, 1, 0, 0},
				 	   {0, 0, 1, 0},
				 	   {0, 0, 0, 1},
				 	   {1, 0, 0, 0}}; // TODO: QR decomposition fails for this case
		double[][] a2 = {{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}};
		double[][] b = {{0, 0, 1, 3},
						{0, 0, 2, 4},
						{1, 2, 0, 0},
						{3, 4, 0, 0}};
		Matrix A = new Matrix(b);
		Matrix[] QR = QR(A, false);
		Matrix Q = QR[0];
		Matrix R = QR[1];
		Matrix prod = QR[0].mult(QR[1]);
		
		Matrix.print("A:\n", A, "\n\n");
		Matrix.print("Q:\n", Q, "\n\n");
		Matrix.print("R:\n", R, "\n\n");
		Matrix.println(prod);

		Matrix.print("----------------------------------------------------------------------\n\n");
	}
}
