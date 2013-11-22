package gep_dc;

public interface GPObject  {
	public boolean isOperator();
	public boolean isTerminal();
	public String toString();
	public double eval();
}
