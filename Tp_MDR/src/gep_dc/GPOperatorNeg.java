package gep_dc;

public class GPOperatorNeg extends GPUnaryOperator {

	public String toString() {
		return "Neg";
	}
	
	public double eval(double x) {
		return x*(-1);
	}

	public double eval() {
		System.out.println("Error: add must have one arguments");
		return 0;
	}
}
