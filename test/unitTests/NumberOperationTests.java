package unitTests;

import java.util.ArrayList;

import linalg.complex_number.CNumber;

public class NumberOperationTests {

	public static void main(String[] args) {
		ArrayList<CNumber> nums = new ArrayList<CNumber>();
		
		nums.add(CNumber.ZERO);
		nums.add(CNumber.ONE);
		nums.add(CNumber.IMAGINARY_UNIT);
		nums.add(new CNumber("23"));
		nums.add(new CNumber("-2i"));
		nums.add(new CNumber("2.4 + 34.6i"));
		
		conjugateTests(nums);
	}
	
	public static void conjugateTests(ArrayList<CNumber> values) {
		
		ArrayList<CNumber> checks = new ArrayList<CNumber>();
		
		CNumber value;
		
		// Adding verified complex conjugates to a check list
		checks.add(new CNumber("0"));
		checks.add(new CNumber("1"));
		checks.add(new CNumber("-i"));
		checks.add(new CNumber("23"));
		checks.add(new CNumber("2i"));
		checks.add(new CNumber("2.4 - 34.6i"));
		
		for(int i = 0; i < values.size(); i++) {
			value = CNumber.conjugate(values.get(i));
			
			if(!value.equals(checks.get(i))) {
				System.out.println("Failed test in 'conjugateTests': Got " + value + " for " + values.get(i)
				+ " but was expecting " + checks.get(i));
				System.exit(1);
			}
		}
		
		System.out.println("All tests in 'conjugateTests' passed.");
	}
	
	public static void addTests() {
		// TODO: Tests here
	}
	
	public static void subTests() {
		// TODO: Tests here
	}
	
	public static void multTests() {
		// TODO: Tests here
	}
	
}
