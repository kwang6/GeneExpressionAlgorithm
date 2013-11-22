package gep_dc;

public abstract class GPBinaryOperator  extends java.lang.Object implements GPOperator {

	public GPBinaryOperator() {
		// TODO Auto-generated constructor stub
	}

	public abstract double eval(double x, double y);
	
	public boolean isOperator() {
		return true;
	}
	
	public boolean isBinaryOperator() {
		return true;
	}

	public boolean isTerminal() {
		return false;
	}
}
