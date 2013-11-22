package gep_dc;

public class GPOperatorMul extends GPBinaryOperator implements GPOperator {

	public String toString() {
		return "*";
	}
	
	public double eval(double x, double y) {
		return x*y;
	}
	
	public double eval() {
		System.out.println("Error: mul must have two arguments");
		return 0;
	}
}
