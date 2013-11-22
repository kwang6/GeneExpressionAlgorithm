package gep_dc;

public class GPOperatorLog extends GPUnaryOperator {

	public String toString() {
		return "log";
	}
	
	public double eval(double x) {
		return Math.log(x);
	}

	public double eval() {
		System.out.println("Error: add must have one arguments");
		return 0;
	}
}
