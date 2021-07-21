package linalg;

import linalg.complex_number.CNumber;


class BinaryOpp implements MatrixOperations {
	
	
	private static final int SAME_DIM = 0;
	private static final int SAME_COL_AND_ROW = 1;
	
	
	/**
	 * Note: This method exist to reduce total lines of code only
	 * 
	 * @param A
	 * @param B
	 * @param subtract
	 * @return 
	 * @return
	 */
	protected static Matrix compute(Matrix A, Matrix B, int opp) {
		
		if(opp < 0 || opp > 4) {
			throw new IllegalArgumentException("Invalid Opperation number. Must be in [0, 4]");
		}
		
		CNumber[][] result = null;
		
		if(opp == ADD_OPP || opp == SUB_OPP || opp == ELEM_MULT_OPP || opp == ELEM_DIV_OPP) {
			dimensionCheck(A, B, SAME_DIM);
			
			result = new CNumber[A.m][A.n];
			
			for(int i = 0; i < result.length; i++) {
				for(int j = 0; j < result[0].length; j++) {
					if(opp == ADD_OPP) {
						result[i][j] = CNumber.add(A.entries[i][j], B.entries[i][j]);
					}
					else if(opp == SUB_OPP) {
						result[i][j] = CNumber.subtract(A.entries[i][j], B.entries[i][j]);
					}
					else if(opp == ELEM_MULT_OPP) {
						result[i][j] = CNumber.multiply(A.entries[i][j], B.entries[i][j]);
					}
					else if(opp == ELEM_DIV_OPP) {
						result[i][j] = CNumber.divide(A.entries[i][j], B.entries[i][j]);
					}
				}
			}
		}
		
		return new Matrix(result);
	}
	
	
	/**
	 * TODO: This probably belongs in its own class
	 * Compares dimensions of two matrices. If they differ, an exception is thrown.
	 * 
	 * @param B - Another matrix
	 */
	private static void dimensionCheck(Matrix A, Matrix B, int type) {
		if(type == SAME_DIM) {
			if(!A.sameShape(B)) { // Checks for equvalent dimensions
				throw new IllegalArgumentException(
						"Shape mismatch. Expecting matrices to have equivalent "
						+ "shapes but recieved " + A.shape() + " and " + B.shape() + "."
						);
			}
		}
		else if(type == SAME_COL_AND_ROW) {
			if(A.entries[0].length != B.entries.length) {
				throw new IllegalArgumentException("Number of columns in first matrix must match \n"
						+ "number of rows in seccond matrix but got " + A.shape() + " and " + B.shape() + ".");
			}
		}
		else {
			throw new IllegalArgumentException("Invalid type. Must be in [0, 1]");
		}	
	}
	
	// FOR DEVELOPMENT TESTING ONLY //
	public static void main(String args[]) {
		
	}
}
