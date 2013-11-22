package gep_dc;

import java.lang.Math;

public class GPOperatorExp extends GPUnaryOperator {

	public String toString() {
		return "exp";
	}
	
	public double eval(double x) {
		return Math.exp(x);
	}

	public double eval() {
		System.out.println("Error: exp must have one argument");
		return 0;
	}
}
