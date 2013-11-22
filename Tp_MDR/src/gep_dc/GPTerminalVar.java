package gep_dc;

public class GPTerminalVar extends GPTerminal {

	private static GepData data;
	private int data_column;
	private String name;
	private double last_value = 0.0;
	
	public GPTerminalVar(GepData data, int column, String name) {
		this.data = data;
		this.data_column = column;
		this.name = name;
	}
	
	public double eval() {
		return last_value;
	}
	
	public double eval(int indexSubstance) {
		this.last_value = data.getData(indexSubstance,data_column);
		return this.last_value;
	}
	
	public String toString() {
		return "["+this.name+"="+String.valueOf(this.last_value)+"]";
	}

	public boolean isOperator()  {
		return false;
	}
	
	public boolean isVariable() {
		return true;
	}
}
