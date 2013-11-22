package gep_dc;

public abstract class GPUnaryOperator  extends java.lang.Object implements GPOperator {

	public GPUnaryOperator() {
		// TODO Auto-generated constructor stub
	}

	public abstract double eval(double x);
	
	public boolean isOperator() {
		return true;
	}
	
	public boolean isBinaryOperator() {
		return false;
	}

	public boolean isTerminal() {
		return false;
	}
}
