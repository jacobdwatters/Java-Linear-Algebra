package linalg;

/**
 * A place to define various print options for Matrices and vectors.<br>
 * <pre>
 *     Supported Print Options:
 *     		- {@link #PADDING}
 *     		- {@link #MAX_ROWS}
 *     		- {@link #MAX_COLUMNS}
 * </pre>
 * 
 * @author Jacob Watters
 */
public class PrintOptions {

	// Hide public constructor for the utility class.
	private PrintOptions() {
		throw new IllegalStateException("This utility class can not be instantiated.");
	}

	/**
	 * Padding between each element of matrix.<br><br>
	 * 
	 * Default Value: 2
	 */
	public static int PADDING = 2;
	
	/**
	 * Maximum number of rows to print from a matrix.
	 * If a matrix has more rows than this value, rows with
	 * indices larger than this value, except for the last row, 
	 * will not be printed.
	 * 
	 * The last row is always printed<br><br>
	 * 
	 * Default Value: 10
	 */
	public static int MAX_ROWS = 10;
	
	
	/**
	 * Maximum number of columns to print from a matrix.
	 * If a matrix has more columns than this value, columns with
	 * indices larger than this value, except for the last column, 
	 * will not be printed. <br>
	 *
	 * The last column is always printed.<br><br>
	 * 
	 * Default Value: 10
	 */
	public static int MAX_COLUMNS = 10;


	/**
	 * Precision of the printed matrix values. i.e. the number of decimal places printed. <br><br>
	 *
	 * Default Value: 20.
	 */
	public static int PRECISION = 20; // Todo: implement this in the Matrix.toString() using Matrix.round(PRECISION) 
}
