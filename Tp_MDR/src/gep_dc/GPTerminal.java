package gep_dc;

public abstract class GPTerminal extends java.lang.Object implements GPObject{
	
	public abstract double eval();
	
	public boolean isOperator() {
		return false;
	}

	public boolean isTerminal() {
		return true;
	}
	
	public abstract boolean isVariable();
}
