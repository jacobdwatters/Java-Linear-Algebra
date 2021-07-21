package linalg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import linalg.complex_number.CNumber;
import linalg.util.ArrayUtils;
import linalg.util.Parser;
import linalg.util.StringUtils;


/**
 * Provides several methods for construction, manipulations, operations, and comparisons of complex or real matrices.
 * All matrices, even those constructed with int[][] or double[][] will have entries of the type {@link linalg.complex_number.CNumber CNumber}
 * 
 * @author Jacob Watters
 */
public class Matrix implements MatrixOperations, MatrixManipulations, MatrixProperties, 
					MatrixComparisons {
	
	protected String shape; // String representation of matrix dimensions. e.g. "<numRows>x<numCols>"
	
	/**
	 * Number of rows in matrix 
	 */
	protected int m;
	
	/**
	 * Number of columns in matrix 
	 */
	protected int n;
	
	protected CNumber[][] entries; // Entries of matrix
	
	/**
	 * Creates an empty matrix with zero rows and zero columns
	 */
	public Matrix() { // Default Constructor
		entries = new CNumber[0][0];
		m = 0;
		n = 0;
		shape = m + "x" + n;
	}
	
	
	/**
	 * Creates a square matrix of specified size, filled with zeros.
	 * 
	 * @param size - size of the square matrix
	 */
	public Matrix(int size) {
		if(size < 0) {
			throw new IllegalArgumentException("Matrix size must be "
					+ "non-negative but recieved " + size);
		}
		
		this.m = size;
		this.n = size;
		shape = m + "x" + n;
		entries = ArrayUtils.zeros(m, n);
	}
	
	
	/**
	 * Creates a square matrix of specified size, filled with specified number.
	 * 
	 * @param size - size of the square matrix
	 * @param s - complex value of matrix entries.
	 */
	public Matrix(int size, CNumber s) {
		if(size < 0) {
			throw new IllegalArgumentException("Matrix size must be "
					+ "non-negative but recieved " + size);
		}
		
		this.m = size;
		this.n = size;
		this.entries = new CNumber[m][n];
		shape = m + "x" + n;
		
		for(int i = 0; i<m; i++) {
			for(int j = 0; j<n; j++) {
				entries[i][j] = s;
			}
		}
	}
	
	
	/**
	 * Creates a matrix with given number of rows and columns filled with zeros
	 * 
	 * @param m - number of rows in matrix
	 * @param n - number of columns in matrix
	 */
	public Matrix(int m, int n) {
		if(m < 0 || n < 0) {
			throw new IllegalArgumentException("Matrix dimensions must be "
					+ "non-negative but recieved " + m + "x" + n);
		}
		this.m = m;
		this.n = n;
		shape = m + "x" + n;
		entries = ArrayUtils.zeros(m, n);
	}
	
	
	/**
	 * Creates a matrix where all entries are same value.
	 * 
	 * @param m - number of rows in matrix
	 * @param n - number of columns in matrix
	 * @param s - complex value of matrix entries.
	 */
	public Matrix(int m, int n, CNumber s) {
		if(m < 0 || n < 0) {
			throw new IllegalArgumentException("Matrix dimensions must be "
					+ "positive integers but recieved " + m + "x" + n);
		}
		
		this.entries = new CNumber[m][n];
		this.m = m;
		this.n = n;
		
		CNumber S = new CNumber(s);
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				entries[i][j] = S;
			}
		}
		
		shape = m + "x" + n;
	}
	
	
	/**
	 * Creates a matrix where all entries are same value.
	 * 
	 * @param m - number of rows in matrix
	 * @param n - number of columns in matrix
	 * @param s - double value of matrix entries.
	 */
	public Matrix(int m, int n, double s) {
		if(m < 0 || n < 0) {
			throw new IllegalArgumentException("Matrix dimensions must be "
					+ "positive integers but recieved " + m + "x" + n);
		}
		
		this.entries = new CNumber[m][n];
		this.m = m;
		this.n = n;
		
		CNumber S = new CNumber(s);
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				entries[i][j] = S;
			}
		}
		
		shape = m + "x" + n;
	}
	
	
	/**
	 * Creates a matrix that is a copy of matrix A.
	 * 
	 * @param A - A matrix
	 */
	public Matrix(Matrix A) {
		shape = A.shape();	
		m = A.numRows();
		n = A.numCols();
		entries = A.copy().entries;
	}

	
	/**
	 * Creates matrix and fills it with the contents of entries.
	 * 
	 * @param entries - 2d array of Strings representing real or complex numbers.
	 */
	public Matrix(String[][] entries) {
		m = entries.length;
		n = entries[0].length;
		shape = m + "x" + n;
		this.entries = new CNumber[m][n];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				this.entries[i][j] = new CNumber(entries[i][j]);
			}
		}
	}
	
	
	/**
	 * Creates matrix and copies contents of entries into matrix.
	 * 
	 * @param entries - 2d array of Number objects.
	 */
	public Matrix(CNumber[][] entries) {
		m = entries.length;
		n = entries[0].length;
		shape = m + "x" + n;
		this.entries = new CNumber[m][n];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				this.entries[i][j] = entries[i][j];
			}
		}
	}
	
	
	/**
	 * Creates matrix and copies contents of entries into matrix.
	 * 
	 * @param entries - 2d array of double.
	 */
	public Matrix(double[][] entries) {
		m = entries.length;
		n = entries[0].length;
		shape = m + "x" + n;
		this.entries = new CNumber[m][n];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				this.entries[i][j] = new CNumber(entries[i][j]);
			}
		}
	}
	
	
	/**
	 * Creates matrix and copies contents of entries into matrix.
	 * 
	 * @param entries - 2d array of double.
	 */
	public Matrix(int[][] entries) {
		m = entries.length;
		n = entries[0].length;
		shape = m + "x" + n;
		this.entries = new CNumber[m][n];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				this.entries[i][j] = new CNumber(entries[i][j]);
			}
		}
	}
	
	
	/**
	 * Creates a matrix with given shape filled with zeros.
	 * 
	 * @param shape - shape of matrix.
	 */
	public Matrix(String shape) {
		int[] dimensions = Parser.parseShape(shape);
		this.shape = shape.replaceAll(" ", "");
		m = dimensions[0];
		n = dimensions[1];
		entries = ArrayUtils.zeros(m, n);
	}
	
	
	/**
	 * Creates a matrix with given shape where all entries are same value.
	 * 
	 * @param shape - shape of matrix.
	 * @param s - complex value of matrix entries.
	 */
	public Matrix(String shape, CNumber s) {
		int[] dimensions = Parser.parseShape(shape);
		this.shape = shape.replaceAll(" ", "");
		m = dimensions[0];
		n = dimensions[1];
		entries = ArrayUtils.zeros(m, n);
		CNumber S = new CNumber(s);
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				entries[i][j] = S;
			}
		}
	}
	
	
	/**
	 * Creates a matrix with given shape where all entries are same value.
	 * 
	 * @param shape - shape of matrix.
	 * @param s - double value of matrix entries.
	 */
	public Matrix(String shape, double s) {
		int[] dimensions = Parser.parseShape(shape);
		this.shape = shape.replaceAll(" ", "");
		m = dimensions[0];
		n = dimensions[1];
		entries = ArrayUtils.zeros(m, n);
		CNumber S = new CNumber(s);
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				entries[i][j] = S;
			}
		}
	}
	
	
	/**
	 * Constructs a square matrix such that all elements in the matrix are zero.
	 * 
	 * @param size - Size of square matrix.
	 * @return A square zero matrix of given size.
	 */
	public static Matrix zeros(int size) {
		double[][] zeros = new double[size][size];
		return new Matrix(zeros);
	}
	
	
	/**
	 * Constructs a matrix with given number of rows and columns such that
	 * all elements in the matrix are zero.
	 * 
	 * @param numRows - number of rows to be in zero matrix.
	 * @param numCols - number of columns to be in zero matrix.
	 * @return A zero matrix with given number of rows and columns.
	 */
	public static Matrix zeros(int numRows, int numCols) {
		double[][] zeros = new double[numRows][numCols];
		return new Matrix(zeros);
	}
	
	
	/**
	 * Constructs a matrix with given number of rows and columns such that
	 * all elements in the matrix are zero.
	 * 
	 * @param shpae - Shape of zero matrix.
	 * @return A zero matrix with given number of rows and columns.
	 */
	public static Matrix Zeros(String shape) {
		int[] dimensions = Parser.parseShape(shape);
		int m = dimensions[0];
		int n = dimensions[1];
		double[][] zeros = new double[m][n];
		return new Matrix(zeros);
	}
	
	
	/**
	 * Constructs a square matrix such that all elements in the matrix are one.
	 * 
	 * @param size - Size of square matrix.
	 * @return A square zero matrix of given size.
	 */
	public static Matrix ones(int size) {
		double[][] ones = new double[size][size];
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				ones[i][j] = 1;
			}
		}
		
		return new Matrix(ones);
	}
	
	
	/**
	 * Constructs a matrix with given number of rows and columns such that
	 * all elements in the matrix are one.
	 * 
	 * @param numRows - number of rows to be in zero matrix.
	 * @param numCols - number of columns to be in zero matrix.
	 * @return A zero matrix with given number of rows and columns.
	 */
	public static Matrix ones(int numRows, int numCols) {
		double[][] ones = new double[numRows][numCols];
		
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				ones[i][j] = 1;
			}
		}
		
		return new Matrix(ones);
	}
	
	
	/**
	 * Constructs a matrix with given number of rows and columns such that
	 * all elements in the matrix are one.
	 * 
	 * @param shpae - Shape of zero matrix.
	 * @return A zero matrix with given number of rows and columns.
	 */
	public static Matrix ones(String shape) {
		int[] dimensions = Parser.parseShape(shape);
		int m = dimensions[0];
		int n = dimensions[1];
		
		double[][] ones = new double[m][n];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				ones[i][j] = 1;
			}
		}
		
		return new Matrix(ones);
	}
	
	
	/**
	 * Generates the identity matrix of a specified size. Same as {@link #I(int) I(int size)}
	 * 
	 * The identity matrix is a square matrix with ones on the main diagonal. 
	 * All other entries are zero.
	 * 
	 * The Identity matrix is the multiplicative identity for matrices. That is, given a matrix B and
	 * the appropriately sized identity matrix, B*I=B.
	 * 
	 * @param size - size of identity matrix. For example, if size = 3, then the
	 * 3x3 identity matrix will be returned. 
	 * @return Identity matrix of specified size.
	 */
	public static Matrix identity(int size) {
		Matrix I = new Matrix(size);
		
		for(int i=0; i<size ; i++) {
			I.entries[i][i] = CNumber.ONE;
		}
		
		return I;
	}
	
	
	/**
	 * Generates the identity matrix of a specified size. Same as {@link #identity(int) identity(int size)}
	 * 
	 * The identity matrix is a square matrix with ones on the main diagonal. 
	 * All other entries are zero.
	 * 
	 * The Identity matrix is the multiplicative identity for matrices. That is, given a matrix B and
	 * the appropriately sized identity matrix, B*I=B.
	 * 
	 * @param size - size of identity matrix. For example, if size = 3, then the
	 * 3x3 identity matrix will be returned. 
	 * @return Identity matrix of specified size.
	 */
	public static Matrix I(int size) {
		return identity(size);
	}
	
	
	/**
	 * Creates an identity like matrix rectangular. That is, a matrix of zeros
	 * with ones along the principle diagonal.
	 * 
	 * @param m - Number of rows in matrix.
	 * @param n - Number of columns in matrix.
	 * @return - Identity-like matrix of specified size.
	 */
	public static Matrix I(int m, int n) {
		Matrix I = new Matrix(m, n);
		
		for(int i=0; i<m && i<n ; i++) {
			I.entries[i][i] = CNumber.ONE;
		}
		
		return I;
	}
	
	
	// TODO: Create radomInt() methods
	// TODO: Maybe all random methods should be moved to a RandomMatrix class
	
	/**
	 * Constructs matrix of given size with random numbers between min and max.
	 * If the boolean flag is true than min and max are used as min and max of complex magnitude.
	 * Otherwise, min and max are used as min and max values of real numbers. 
	 * 
	 * @param shape - Shape of the resulting matrix.
	 * @param min - Lower bound for random number
	 * @param max - Upper bound for random number
	 * @param magnitude_flag - Optional flag to use min and max as bounds for magnitude of complex number.
	 * @return Matrix with random entries of specified size
	 */
	public static Matrix random(String shape, double min, double max, boolean...magnitude_flag) {
		int[] dimensions = Parser.parseShape(shape);
		int rows = dimensions[0];
		int cols = dimensions[1];
		
		return random(rows, cols, min, max, magnitude_flag);
	}
	
	
	/**
	 * Constructs matrix of given size with random numbers between min and max.
	 * If the boolean flag is true than min and max are used as min and max of complex magnitude.
	 * Otherwise, min and max are used as min and max values of real numbers. 
	 * 
	 * @param rows - Number of rows in resulting matrix
	 * @param cols - Number of columns in resulting matrix
	 * @param min - Lower bound for random number
	 * @param max - Upper bound for random number
	 * @param magnitude_flag - Optional flag to use min and max as bounds for magnitude of complex number.
	 * @return Matrix with random entries of specified size
	 */
	// TODO: boolean... should be replaced by method overloading.
	public static Matrix random(int rows, int cols, double min, double max, boolean... magnitude_flag) {
		if(magnitude_flag.length > 0) {
			return new Matrix(ArrayUtils.random(rows, cols, min, max, magnitude_flag[0]));
		}
		else {
			return new Matrix(ArrayUtils.random(rows, cols, min, max));
		}
	}
	
	
	/**
	 * Constructs matrix of given size with random numbers between zero and one.
	 * If the boolean flag is true than min and max are used as min and max of complex magnitude.
	 * Otherwise, min and max are used as min and max values of real numbers. 
	 * 
	 * @param shape - Shape of the resulting matrix.
	 * @param magnitude_flag - Optional flag to generate complex number with magnitude between zero and one.
	 * @return Matrix of specified size with random entries.
	 */
	// TODO: boolean... should be replaced by method overloading.
	public static Matrix random(String shape, boolean... magnitude_flag) {
		int[] dimensions = Parser.parseShape(shape);
		int rows = dimensions[0];
		int cols = dimensions[1];
		
		return random(rows, cols, magnitude_flag);
	}
	
	
	/**
	 * Constructs matrix of given size with random numbers between zero and one.
	 * If the boolean flag is true than min and max are used as min and max of complex magnitude.
	 * Otherwise, min and max are used as min and max values of real numbers. 
	 * 
	 * @param rows - Number of rows in resulting matrix.
	 * @param cols - Number of columns in resulting matrix.
	 * @param magnitude_flag - Optional flag to generate complex number with magnitude between zero and one.
	 * @return Matrix of specified size with random entries.
	 */
	public static Matrix random(int rows, int cols, boolean... magnitude_flag) {
		if(magnitude_flag.length > 0) {
			return new Matrix(ArrayUtils.random(rows, cols, 0, 1, magnitude_flag[0]));
		}
		else {
			return new Matrix(ArrayUtils.random(rows, cols, 0, 1));
		}
	}
	
	
	/**
	 * Constructs matrix of given size with random complex numbers with given magnitude.
	 * 
	 * @param shape - Shape of the resulting matrix
	 * @param mag - Optional flag to generate complex number with magnitude between zero and one.
	 * @return Matrix of specified size with random entries.
	 */
	public static Matrix random(String shape, double mag) {
		int[] dimensions = Parser.parseShape(shape);
		int rows = dimensions[0];
		int cols = dimensions[1];
		
		return random(rows, cols, mag);
	}
	
	
	/**
	 * Constructs matrix of given size with random complex numbers with given magnitude.
	 * 
	 * @param rows - Number of rows in resulting matrix.
	 * @param cols - Number of columns in resulting matrix.
	 * @param mag - Optional flag to generate complex number with magnitude between zero and one.
	 * @return Matrix of specified size with random entries.
	 */
	public static Matrix random(int rows, int cols, double mag) {
		return new Matrix(ArrayUtils.random(rows, cols, mag));
	}
	
	
	/**
	 * Generates a matrix of given size where all entries are normaly distributed random values with
	 * mean of zero and a standard deviation of one.
	 * 
	 * @param rows - Number of rows in resulting matrix.
	 * @param cols - Number of columns in resulting matrix.
	 * @param mag - flag to generate complex number.
	 * @return Matrix of specified size with random entries.
	 */
	public static Matrix randn(int rows, int cols, boolean complex) {
		return new Matrix(ArrayUtils.randn(rows, cols, complex));
	}
	
	
	/**
	 * Generates a random complex matrix with given shape.
	 * 
	 * @param rows - Number of rows in random matrix.
	 * @param cols - Number of columns in random matrix.
	 * @return Complex matrix of specified size with random entries.
	 */
	public static Matrix randomComplex(int rows, int cols) {
		return new Matrix(ArrayUtils.randomComplex(rows, cols));
	}
	
	
	/**
	 * Generates a random orthogonal matrix distributed with Haar measure. A matrix B is orthogonal if and only if
	 * B<sup>T</sup>B = I. 
	 * 
	 * @param n - Size of the random orthogonal matrix.
	 * @return A random orthogonal matrix.
	 */
	public static Matrix randomOrthogonal(int n) {
		Matrix A = Matrix.randn(n, n, false).scalDiv(Math.sqrt(2)); // Random matrix.
		Matrix[] QR = Decompose.QR(A);
		Matrix D = QR[1].diag();
		
		for(int i=0; i<D.m; i++) {
			D.entries[i][i] = CNumber.divide(D.entries[i][i], CNumber.abs(D.entries[i][i]));
		}
		
		return QR[0].mult(D).mult(QR[0]);
	}
	
	
	/**
	 * Generates a random orthogonal matrix distributed with Haar measure. A matrix B is orthogonal if and only if
	 * B<sup>*</sup>B = I. 
	 * 
	 * @param n - Size of the random orthogonal matrix.
	 * @return A random orthogonal matrix.
	 */
	public static Matrix randomUinitary(int n) {
		Matrix A = Matrix.randn(n, n, true).scalDiv(Math.sqrt(2)); // Random matrix.
		Matrix[] QR = Decompose.QR(A);
		Matrix D = QR[1].diag();
		
		for(int i=0; i<D.m; i++) {
			D.entries[i][i] = CNumber.divide(D.entries[i][i], CNumber.abs(D.entries[i][i]));
		}
		
		return QR[0].mult(D).mult(QR[0]);
	}
	
	
	/**
	 * Constructs a copy of the matrix.
	 * 
	 * @return - Copy of matrix.
	 */
	public Matrix copy() {
		return new Matrix(this.entries);
	}
	
	
	/**
	 * Gets values from matrix.
	 * 
	 * @return Returns entries of this matrix as 2d array of Numbers.
	 */
	public CNumber[][] getValues() { return this.entries; }
	
	
	/**
	 * Gets values from matrix converted to doubles.
	 * <br>
	 * Note: using this method will result in the loss the imaginary portion
	 * of any cells in the matrix.
	 * 
	 * @return Returns entries of this matrix as 2d array of doubles.
	 */
	public double[][] getValuesAsDouble() {
		double[][] result = new double[this.m][this.n];
		
		for(int i=0; i < result.length; i++) {
			for(int j=0; j < result.length; j++) {
				result[i][j] = entries[i][j].doubleValue();
			}
		}
		
		return result;
	}
	
	
	/**
	 * Extracts row from matrix.
	 * 
	 * @param rowIndex - Index of row to be returned.
	 * @return extracted row from the matrix.
	 */
	public CNumber[] getRow(int rowIndex) {
		return entries[rowIndex];
	}
	
	
	/**
	 * Extracts column vector from matrix.
	 * 
	 * @param rowIndex - Index of column to be returned.
	 * @return extracted column from the matrix as vector.
	 */
	public Vector getRowAsVector(int rowIndex) {
		Vector col = new Vector(this.n, Vector.ROW_VECTOR);
		
		for(int i = 0; i < this.n; i++) {
			col.entries[0][i] = this.entries[rowIndex][i];
		}
		
		return col;
	}
	
	
	/**
	 * Extracts column from matrix.
	 * 
	 * @param rowIndex - Index of column to be returned.
	 * @return extracted column from the matrix.
	 */
	public CNumber[] getCol(int colIndex) {
		CNumber[] col = new CNumber[this.m];
		
		for(int i = 0; i < this.m; i++) {
			col[i] = this.entries[i][colIndex];
		}
		
		return col;
	}
	
	
	/**
	 * Extracts column vector from matrix.
	 * 
	 * @param rowIndex - Index of column to be returned.
	 * @return extracted column from the matrix as vector.
	 */
	public Vector getColAsVector(int colIndex) {
		Vector col = new Vector(this.m);
		
		for(int i = 0; i < this.m; i++) {
			col.entries[i][0] = this.entries[i][colIndex];
		}
		
		return col;
	}
	
	
	/**
	 * Gets a slice of a matrix. All start values inclusive, all end values exclusive.
	 * 
	 * @param rowStart
	 * @param rowEnd
	 * @param colStart
	 * @param colEnd
	 * @return
	 */
	public Matrix getSlice(int rowStart, int rowEnd, int colStart, int colEnd) {
		Matrix result = new Matrix(rowEnd-rowStart, colEnd-colStart);
		
		for(int i = 0; i < result.entries.length; i++) {
			for(int j = 0; j < result.entries[0].length; j++) {
				result.entries[i][j] = this.entries[i+rowStart][j+colStart];
			}
		}
		
		return result;
	}
	
	
	/**
	 * Gets value from specified location from this matrix.
	 * 
	 * @return Returns data in matrix at given position
	 */
	public CNumber get(int row, int col) { return new CNumber(entries[row][col]); }
	
	
	/**
	 * Gets value at specified location from this matrix converted to a double.
	 * <br>
	 * Note: using this method will result in the loss of the imaginary portion
	 * of the matrix cell.
	 * 
	 * @return - data in matrix at given position as double
	 */
	public double getAsDouble(int row, int col) { return entries[row][col].getReal(); }
	
	
	/**
	 * Prints a list of objects to the standard output using that objects toString() method.
	 * 
	 * @param printList - List of objects to print. Can be Matrix,
	 * Vector, String, etc. 
	 */
	public static void print(Object... printList) {
		for(int i=0; i<printList.length; i++) {
			System.out.print(printList[i].toString());
		}
	}
	
	
	/**
	 * Prints a list of objects to the standard output using that objects toString() method
	 * and a new-line character.
	 * 
	 * @param printList - List of objects to print. Can be Matrix,
	 * Vector, String, etc. 
	 */
	public static void println(Object... printList) {
		for(int i=0; i<printList.length; i++) {
			System.out.print(printList[i].toString());
		}
		
		System.out.print("\n");
	}
	
	
	
	/**
	 * Prints a list of objects to the standard output using that objects toString() method and 
	 * Separating each Object with a specified String.
	 * 
	 * @param seperator - String to print between each Object in <code>pritnList</code>
	 * @param printList - List of objects to print. Can be Matrix,
	 * Vector, String, etc. 
	 */
	public static void printSep(String seperator, Object... printList) {
		for(int i=0; i<printList.length; i++) {
			System.out.print(printList[i].toString() + seperator);
		}
		
		System.out.println();
	}
	
	
	
	/**
	 * Formats matrix contents as a string.
	 * 
	 * @return Matrix as string
	 */
	public String toString() {
		String result = "[";
		
		if(!this.isEmpty()) {
			int max, colWidth;
			List<Integer> maxList = new ArrayList<Integer>();
			
			for(int j=0; j<this.n; j++) { // Get the maximum length string representation for each column.
				List<CNumber> contents = Arrays.asList(this.getCol(j));
				max = contents.stream().map(CNumber::length).max(Integer::compareTo).get();
				maxList.add(max);
			}
			
			for(int i = 0; i < m; i++) {
				if(i >= PrintOptions.MAX_ROWS && i < m-2) {
					result += "  ...\n ";
					i = m-1;
				}	
				
				result += " [";
				
				for(int j = 0; j < n; j++) {		
					
					if(j >= PrintOptions.MAX_COLUMNS && j < n-2) {
						colWidth = 3+PrintOptions.PADDING;
						result += String.format("%-" + colWidth + "s", StringUtils.center("...", colWidth));
						colWidth = maxList.get(n-1)+PrintOptions.PADDING;
						result += String.format("%-" + (colWidth) + "s", StringUtils.center(entries[i][n-1].toString(), colWidth));
						break;
					}
					else {
						colWidth = maxList.get(j)+PrintOptions.PADDING;
						result += String.format("%-" + (colWidth) + "s", StringUtils.center(entries[i][j].toString(), colWidth));
					}
				}
				result += "]\n ";
			}
			
			result = result.substring(0, result.length()-2) + " ]";
		}
		else {
			result += "[]]";
		}
		
		return result;
	}
	
	
	/* FOR DEVELOPMENT TESTING ONLY */
	public static void main(String[] args) {
		int size = 3 ;
		double[][] a = {{5, 0, 0, 4},
						{0, 3, 0, 0},
						{0, 0, 5, 1},
						{4, 0, 1, 6}};
		Matrix A = Matrix.I(5);
		Matrix.print(A);
	}
}
