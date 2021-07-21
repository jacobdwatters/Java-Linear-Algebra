package unitTests;

import java.util.ArrayList;

import linalg.Matrix;
import linalg.PrintOptions;
import linalg.complex_number.CNumber;

public class MatrixConstructorTests {
	public static void main(String[] args) {
		ArrayList<Matrix> matrices = new ArrayList<Matrix>();
		
		PrintOptions.MAX_COLUMNS = 1;
		PrintOptions.MAX_ROWS = 1;
		
		int[][] a = {{1, 2, 3, 1, 2, 3, 4, 5, 6},
			 	 {4, 5, 6, 3, 4, 5, 7, 8, 2},
			 	 {1, 5, 5, 2, 6, 7, 9, 0, 1},
			 	 {3, 4, 5, 2, 6, 7, 8, 9, 2},
			 	 {1, 1, 1, 3, 4, 7, 8, 9, 1},
			 	 {3, 4, 7, 8, 3, 1, 2, 3, 4},
			 	 {3, 5, 6, 8, 1, 3, 5, 9, 1},
			 	 {0, 4, 5, 3, 2, 0, 1, 2, 3},
			 	 {1, 4, 5, 1, 7, 0, 5, 3, 1}};
			
		CNumber[][] b = {{new CNumber("16+2.1i"), new CNumber(CNumber.E), 				new CNumber("14i"), 		new CNumber("12.3")},
						{new CNumber(3), 		new CNumber(CNumber.IMAGINARY_UNIT), 	new CNumber("3+5i"), 	new CNumber(32.1)},
						{new CNumber("2+14.2i"), new CNumber("4 - -2i"), 				new CNumber("1 - 2i"), 	new CNumber(3, 4)},
						{new CNumber("13-4.5i"), new CNumber("115+44.3i"), 			new CNumber("1 + -3i"), 	new CNumber(CNumber.PI)}};
		
		double[][] c = {{2.4}};
		
		String[][] d = {{"5",  		"2-2.3i", 	"4.1 + -3.1i"},
						{"3i", 		"-i", 		"4.1"},
						{"0+0i", 	"0", 		"1"}};
		
		Matrix D = new Matrix(d);
		
		matrices.add(new Matrix(a));
		matrices.add(new Matrix(b));
		matrices.add(new Matrix(c));
		matrices.add(D);
		matrices.add(new Matrix(D));
		matrices.add(new Matrix());
		matrices.add(new Matrix(3));
		matrices.add(new Matrix(4));
		matrices.add(new Matrix(3, new CNumber(2, 3)));
		matrices.add(new Matrix(2, 15));
		matrices.add(new Matrix(2, 15, 1.4));
		matrices.add(new Matrix(2, 15, new CNumber(0, -1)));
		matrices.add(new Matrix("5x2"));
		matrices.add(new Matrix("5x2", 3.2));
		matrices.add(new Matrix("5x2", new CNumber(3.2, 1)));
		
		
		for(Matrix M: matrices) {
			Matrix.printSep("\n\n", M);
		}
	}
}
