package gep_dc;

public class GPTerminalCste extends GPTerminal {
	private double value;
	
	public GPTerminalCste(double value) {
		this.value = value;
	}

	public double eval() {
		return this.value;
	}
	
	public String toString() {
		return "["+String.valueOf(this.value)+"]";
	}

	public boolean isOperator()  {
		return false;
	}
	
	public boolean isVariable() {
		return false;
	}
}
