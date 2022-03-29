package linalg;

import linalg.complex_number.CNumber;


/**
 * A class providing functionality for the Schur decomposition.<br><br>
 *
 * Given a square matrix A, the schur decomposition of A is A = UTU* where U is a unitary matrix, T is an
 * upper triangular matrix and U* denotes the conjugate transpose of U. Further, the columns of U are
 * the eigenvectors of A and the diagonal elements of T are the eigenvalues of A.
 */
class SchurDecomposition {

	private SchurDecomposition() { // hide public constructor
		throw new IllegalStateException("Utility class");
	}


	/**
	 * Computes the schur decomposition of a matrix. That is, computes unitary matrix U and an
	 * upper triangular matrix T such that A = UTU* where U* is the conjugate transpose of U.
	 * The columns of U are the eigenvectors of A, and the diagonal of T are the eigenvalues of A.
	 *
	 * @param A Matrix to compute the schur decomposition of.
	 * @param complex Flag for computing the complex schur form or the real schur form.<br>
	 *                - If true, the complex schur form will be computed.<br>
	 *                - If false, the real schur form will be computed.
	 * @param computeU Flag for computing the unitary matrix in the schur decomposition.<br>
	 *                 - If true, the unitary matrix is computed.<br>
	 *                 - If false, the unitary matrix is NOT computed.
	 * @return If computeU is true, then an array of matrices containing {U, T, U*} is returned.
	 * If computeU is false, then an array of matrices containing {T} is returned.
	 */
	protected static Matrix[] schurDecomp(Matrix A, boolean complex, boolean computeU) {
		Matrix H = A.hessu(); // Convert the matrix to upper Hessenberg form.
		Matrix[] schur;

		double tol = 1E-14; // TODO: Add overloaded method that allows tol and countTol to be passed
		int countTol = 1000; // Maximum number of iterations for
		int count;
		int n = A.m-1;
		
		Matrix lam = Matrix.zeros(A.m, 1); // Stores eigenvalues of A.
		Matrix QR[] = null;
		Matrix T = Matrix.I(A.m);
		Matrix U = Matrix.I(A.m);

		// TODO: Should this be changed to the Wilkinson shift
		CNumber mu = null, // mu is the shift for the shifted QR Algorithm. Currently, this shift is computed using Rayleigh quotient.
				disc, 
				two = new CNumber(2); 
		
		while(n>0) {
			count = 0;


			// Apply the QR algorithm
			while(H.getSlice(n, n+1, 0, n).abs().max().re > tol && count<countTol) {
				count++;
				mu = H.entries[n][n];
				// TODO: is the rounding necessary
				QR = Decompose.QR(H.sub(Matrix.I(n+1).scalMult(mu)).round(14)); // Compute the QR factorization of A with a shift
				H = QR[1].mult(QR[0]).add(Matrix.I(n+1).scalMult(mu).round(14)); // Reverse the shift.

				if(computeU) {
					U = U.mult(Matrix.I(
							U.numRows()).setSliceCopy(U.numRows()-QR[0].numRows(),
							U.numCols()-QR[0].numCols(), QR[0])
					);
				}
			}

			T.setSlice(H.m-(n+1), H.m-(n+1), H);
			
			if(count<countTol) { // Then we have an isolated 1-by-1 block
				lam.entries[n][0] = H.entries[n][n]; // Store eigenvalue
				n--;
				H = H.getSlice(0, n+1, 0, n+1); // Deflate by one
			} 
			else { // Then we have an isolated 2-by-2 block
				
				if(complex) { // Then convert to the complex schur form.
					disc = 	CNumber.add(
							CNumber.pow(
								CNumber.subtract(H.entries[n-1][n-1], H.entries[n][n]),
								2
							),
							CNumber.multiply(
									new CNumber(4), 
									CNumber.multiply(H.entries[n][n-1], H.entries[n-1][n])
							)
						
						);
					lam.entries[n][0] = CNumber.divide(
											CNumber.add(
													CNumber.add(H.entries[n-1][n-1], H.entries[n][n]),
													CNumber.sqrt(disc)
											),
											two
										);
					
					lam.entries[n-1][0] = CNumber.divide(
											CNumber.subtract(
													CNumber.add(H.entries[n-1][n-1], H.entries[n][n]),
													CNumber.sqrt(disc)
											),
											two
										  );
					
					Matrix zero = Matrix.zeros(2);
					T.setSlice(n-1, n-1, zero);
					
					T.entries[n][n] = lam.entries[n][0];
					T.entries[n-1][n-1] = lam.entries[n-1][0];
				}
				
				n=n-2;
				H = H.getSlice(0, n+1, 0, n+1); // Deflate by 2
			}
		}
		
		if(n>-1) { // Then only a 1-by-1 block remains
			lam.entries[0][0] = H.entries[0][0];
		}
		
		T.roundToZero(13);

		if(computeU) {
			schur = new Matrix[3];
			schur[0] = U;
			schur[1] = T;
			schur[2] = U.H();
		} else {
			schur = new Matrix[1];
			schur[0] = T;
		}

		return schur;
	}
}
