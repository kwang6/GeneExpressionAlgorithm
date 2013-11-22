package gep_dc;

import java.util.*;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class GepExpressionNode implements MutableTreeNode {
	
	private ArrayList<MutableTreeNode> children;

	private MutableTreeNode parent;

	private GPObject userObject;

	public GepExpressionNode() {
		this(null);
	}

	public GepExpressionNode(GPObject userObject) {
		super();
		children = new ArrayList<MutableTreeNode>();
		this.userObject = userObject;
		this.parent = null;
	}

	public void add(MutableTreeNode child) {
		children.add(children.size(), child);
		child.setParent(this);
	}
	
	public void insert(MutableTreeNode child, int index) {
		if (index >= 0 && index < children.size()) {
			children.add(index, child);
		}
		else System.out.println("Error: child not inserted");
	}

	public void remove(int index) {
		MutableTreeNode node = (MutableTreeNode) children.remove(index);
		if (node != null) {
			node.setParent(null);
		}
	}
	
	public double eval(int indexSubstance) {
		if ( userObject.isTerminal() ) {
			if ( ((GPTerminal)(this.userObject)).isVariable() ) { 
				return ((GPTerminalVar)(this.userObject)).eval(indexSubstance);
			} else {
				return userObject.eval();
			}
		}
		if ( userObject.isOperator() ) {
			if ( ((GPOperator)userObject).isBinaryOperator() ) {
				return ((GPBinaryOperator)userObject).eval(
						((GepExpressionNode)(this.getChildAt(0))).eval(indexSubstance),
								((GepExpressionNode)(this.getChildAt(1))).eval(indexSubstance));
			} else { //  non binary
				return ((GPUnaryOperator)userObject).eval(
						((GepExpressionNode)(this.getChildAt(0))).eval(indexSubstance));
			}
		} else { 
			return 0;
		}
			
	}

	public void remove(MutableTreeNode node) {
		if (children.contains(node)) {
			children.remove(node);
		}
	}

	public void setUserObject(Object userObject) {
		this.userObject = (GPObject)userObject;
	}

	public void removeFromParent() {
		parent.remove(this);
	}

	public void setParent(MutableTreeNode newParent) {
		this.parent = newParent;
		//parent.insert(this, parent.getChildCount());
	}

	public TreeNode getChildAt(int childIndex) {
		return (TreeNode) children.get(childIndex);

	}

	public int getChildCount() {
		return children.size();
	}

	public TreeNode getParent() {
		return parent;
	}

	public int getIndex(TreeNode node) {
		if(!(children.contains(node)))
			return -1;
		return children.indexOf(node);
	}

	public boolean getAllowsChildren() {
		return false;
	}

	public boolean isLeaf() {
		return children.size()==0;
	}

	public Enumeration<MutableTreeNode> children() {
		return Collections.enumeration(children);
	}

	public Object getUserObject() {
		return userObject;
	}

	public String toString( ) {
		String s= userObject.toString();
		if ( this.userObject.isOperator() ) {
			s +=  "(";
			Iterator<MutableTreeNode> itr = children.iterator();
			while (itr.hasNext()) {
				GepExpressionNode element = (GepExpressionNode) itr.next();
				s += element.toString();
				if (itr.hasNext()) s+= ",";
			}
			s += ")";
		}
		return s;
	}

}