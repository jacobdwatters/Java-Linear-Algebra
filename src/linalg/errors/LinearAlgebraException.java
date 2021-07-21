package linalg.errors;

public class LinearAlgebraException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LinearAlgebraException(String errorMsg) {
		super(errorMsg);
	}
}
