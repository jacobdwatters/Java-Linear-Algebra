package unitTests;

import java.util.ArrayList;

import linalg.complex_number.CNumber;

public class NumberConstructorTests {
	public static void main(String[] args) {
		ArrayList<CNumber> nums = new ArrayList<CNumber>();
		
		CNumber a = new CNumber("2.4 + -34.6i");
		CNumber b = new CNumber("-3.4 - 12i");
		CNumber c = new CNumber(0, 1);
		CNumber d = new CNumber(3, 4);
		
		nums.add(new CNumber());
		
		nums.add(new CNumber("23"));
		nums.add(new CNumber("-34"));
		nums.add(new CNumber("234.0932"));
		nums.add(new CNumber("-11.34645"));
		nums.add(new CNumber("i"));
		nums.add(new CNumber("-i"));
		nums.add(new CNumber("234i"));
		nums.add(new CNumber("-2i"));
		nums.add(new CNumber("2.093i"));
		nums.add(new CNumber("-0.0093i"));
		nums.add(new CNumber("1.3 + 0i"));
		nums.add(new CNumber("2 + 0.0i"));
		nums.add(new CNumber("3 - 0i"));
		nums.add(new CNumber("4 - 0.0i"));
		nums.add(new CNumber("1 + 2i"));	
		nums.add(new CNumber("1 - 2i"));
		nums.add(new CNumber("1 - -2i"));
		nums.add(new CNumber("1 + -2i"));
		nums.add(new CNumber("2.4 + 34.6i"));
		nums.add(new CNumber("2.4 - 34.6i"));
		nums.add(new CNumber("2.4 - -34.6i"));
		nums.add(new CNumber("2.4 + -34.6i"));
		nums.add(new CNumber("0 + 0i"));
		nums.add(new CNumber("0i"));
		nums.add(new CNumber("0"));
		
		nums.add(new CNumber(1, 4));
		nums.add(new CNumber(0, 0));
		nums.add(new CNumber(140.3, 141.3452));
		nums.add(new CNumber(113, 0));
		nums.add(new CNumber(0, -1.2));
		nums.add(new CNumber(1, -4));
		nums.add(new CNumber(-140.3, 141.3452));
		nums.add(new CNumber(140.3, -141.3452));
		nums.add(new CNumber(-1.78, 0));
		nums.add(new CNumber(0, -1.2));
		
		nums.add(new CNumber(21));
		nums.add(new CNumber(234.2));
		nums.add(new CNumber(-13));
		nums.add(new CNumber(-3.523));
		
		nums.add(new CNumber(a));
		nums.add(new CNumber(b));
		nums.add(new CNumber(c));
		nums.add(new CNumber(d));
		
		
		for(CNumber n : nums) {
			System.out.println(n);
		}
	}
}
