package unitTests;

import linalg.Matrix;
import linalg.complex_number.CNumber;
import linalg.util.ExampleMatrices;

public class MatrixOperationsTests extends ExampleMatrices {
	public static void main(String[] args) {
//		determinantTests();
//		echelonTests();
		inverseTests();
	}
	
	public static void echelonTests() {
		Matrix A = new Matrix(MATRIX_8);
		Matrix B = new Matrix(MATRIX_6);
		
		
		Matrix.print("A:\n", A, "\n\nRREF:\n", A.rref(), 
				"\n---------------------------------------------------------------\n\n");
		Matrix.print(B.rref(), 
				"\n---------------------------------------------------------------\n\n");
	}
	
	public static void determinantTests() {
		int[][] a = {{1, 2},
				 {3, 4}};
	
		CNumber[][] b = {{new CNumber("i"), new CNumber(2)}, 
						{new CNumber(3), new CNumber(4)}};
		
		int[][] c = {{1, 2, 3},
				 	{4, 5, 6},
				 	{7, 8, 9}};
		
		CNumber[][] d = {{new CNumber("i"), new CNumber(2), new CNumber(3)},
						{new CNumber(4), new CNumber("5-2.1i"), new CNumber(6)},
						{new CNumber(7), new CNumber(8), new CNumber("-15i")}};
		
		Matrix A = new Matrix(a);
		Matrix B = new Matrix(b);
		Matrix C = new Matrix(c);
		Matrix D = new Matrix(d);
		
		CNumber detA = A.det();
		CNumber detB = B.det();
		CNumber detC = C.det();
		CNumber detD = D.det();
		
		Matrix.print("A:\n", A, "\n");
		System.out.println("Det: " + detA + "\n\n");
		
		Matrix.print("B:\n", B, "\n");
		System.out.println("Det: " + detB + "\n\n");
		
		Matrix.print("C:\n", C, "\n");
		System.out.println("Det: " + detC + "\n\n");
		
		Matrix.print("D:\n", D, "\n");
		System.out.println("Det: " + detD + "\n\n");
	}
	
	
	public static void inverseTests() {
		int[][] b = {{1, 2, 3},
			 	 	 {4, 5, 6},
			 	 	 {7, 8, 9}};
		
		
		
		Matrix B = new Matrix(b);
		Matrix BI = B.inverse();
		
		Matrix.print("B:\n", B, "\n\n");
		Matrix.print("B^-1:\n", BI, "\n\n");
		Matrix.print("B*B^-1 (Rounded to 8 decimal places):\n", B.mult(BI).round(8), "\n\n");
	}
}
