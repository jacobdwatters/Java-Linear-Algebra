package unitTests;

import linalg.Decompose;
import linalg.Matrix;
import linalg.complex_number.CNumber;

public class MatrixFactorizationTests {

	final static double[][] a = 
		{{11, 		6.011, 		4.656841},
		 {6.011, 	4.656841, 	4.11907321},
		 {4.656841, 4.11907321, 3.9225424}};
	
	static Matrix A = new Matrix(a);
	
	final static int[][] b =
		{{1, 2, 3, 1, 2, 3, 4, 5, 6},
	 	 {4, 5, 6, 3, 4, 5, 7, 8, 2},
	 	 {1, 5, 5, 2, 6, 7, 9, 0, 1},
	 	 {3, 4, 5, 2, 6, 7, 8, 9, 2},
	 	 {1, 1, 1, 3, 4, 7, 8, 9, 1},
	 	 {3, 4, 7, 8, 3, 1, 2, 3, 4},
	 	 {3, 5, 6, 8, 1, 3, 5, 9, 1},
	 	 {0, 4, 5, 3, 2, 0, 1, 2, 3},
	 	 {1, 4, 5, 1, 7, 0, 5, 3, 1}};
	
	static Matrix B = new Matrix(b);
	
	public static void main(String[] args) {
//		LUTest();
		QRTest();
//		choleskyTest();
	}
	
	
	public static void LUTest() {
		double[][] a = {{4, -20, -12},
						{-8, 45, 44},
						{20, -105, -79}};


		Matrix A = new Matrix(a);
		Matrix[] LU = Decompose.LU(A);
		Matrix[] LUP = Decompose.LUP(A);
		Matrix[] LUPQ = Decompose.LUPQ(A);
		Matrix[] LDU = Decompose.LDU(A);
		
		Matrix.println("A:\n", A, "\n\n--------------------------------------");
		
		Matrix.println("L:\n", LU[0], "\n\n");
		Matrix.println("U:\n", LU[1], "\n\n");
		Matrix.println("Product:\n", LU[0].mult(LU[1]), "\n\n--------------------------------------");
		
		Matrix.println("L:\n", LUP[0], "\n\n");
		Matrix.println("U:\n", LUP[1], "\n\n");
		Matrix.println("P:\n", LUP[2], "\n\n");
		Matrix.println("Product:\n", LUP[2].mult(A), "\n\n", LUP[0].mult(LUP[1]), "\n\n--------------------------------------");
		
		Matrix.println("L:\n", LUPQ[0], "\n\n");
		Matrix.println("U:\n", LUPQ[1], "\n\n");
		Matrix.println("P:\n", LUPQ[2], "\n\n");
		Matrix.println("Q:\n", LUPQ[3], "\n\n");
		Matrix.println("Product:\n", LUPQ[2].mult(A).mult(LUPQ[3]), "\n\n", LUPQ[0].mult(LUPQ[1]), "\n\n--------------------------------------");
		
		Matrix.println("L:\n", LDU[0], "\n\n");
		Matrix.println("D:\n", LDU[1], "\n\n");
		Matrix.println("U:\n", LDU[2], "\n\n");
		Matrix.println("Product:\n", LDU[0].mult(LDU[1]).mult(LDU[2]), "\n\n--------------------------------------");
	}
	
	
	public static void QRTest() {
		double[][] a = {{1, -1,  4},
						{1,  4, -2},
						{1,  4,  2}};
		
		CNumber[][] ac = {{new CNumber("2+i"), new CNumber("1")},
						  {new CNumber("1"), new CNumber("1-i")}};
		
		
		Matrix A = new Matrix(ac);
		Matrix[] QR = Decompose.QR(A, true);
		
		Matrix.print("QR Test1:\nA:\n", A, "\n\n");
		Matrix.print("Q:\n", QR[0].round(10), "\n\n");
		Matrix.print("R:\n", QR[1].round(10), "\n\n");
		Matrix.print("h:\n", QR[2], "\n\n");
		Matrix.print("Q^H*Q:\n", QR[0].H().mult(QR[0]).round(10), "\n\n");
		Matrix.print("Q*R:\n", QR[0].mult(QR[1]).round(10), "\n\n");
//		Matrix.print("det(A): ", A.det(), "\n\n");
		Matrix.print("----------------------------------------------------------------------\n\n");
	}
	
	
	public static void choleskyTest() {
		double[][] a = {{  4,  12,  -16},
						{ 12,  37,  -43},
						{-16, -43,   98}};

		CNumber[][] m = {{new CNumber("5"), new CNumber("2+3i"), new CNumber("5i")},
						 {new CNumber("2-3i"), new CNumber("7"), new CNumber("1+7i")},
						 {new CNumber("-5i"), new CNumber("1-7i"), new CNumber("12")}};
		
		Matrix M = new Matrix(m);
		Matrix A = new Matrix(a);
		Matrix[] L = Decompose.cholesky(A);
		Matrix[] Lm = Decompose.cholesky(M);
		
		Matrix.print("A:\n", A, "\n\n");
		Matrix.println("L:\n", L[0], "\n\n");
		Matrix.println("L*:\n", L[1], "\n\n");
		Matrix.println("A:\n", L[0].mult(L[1]), "\n\n\n-------------------------------------------");
		
		Matrix.print("M:\n", M, "\n\n");
		Matrix.print("L:\n", Lm[0], "\n\n");
		Matrix.print("L*:\n", Lm[1], "\n\n");
		Matrix.println("M:\n", Lm[0].mult(Lm[1]).round(8), "\n\n\n----------------------------------------------");
	}
}