package gep_dc;

public class GepExpressionTree {
	GepExpressionNode root_node;
	
	public GepExpressionTree(GepExpressionNode root) {
		root_node = root;
	}
	
	public double eval(int indexSubstance) {
		return root_node.eval(indexSubstance);
	}
	
	public void add(GepExpressionNode parent, GepExpressionNode child) {
		parent.add(child);
		child.setParent(parent);
	}
	
	public String toString() {
		String s = new String();
		
		s = root_node.toString();
		return s;
	}
	
}
