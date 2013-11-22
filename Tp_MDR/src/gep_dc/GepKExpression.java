package gep_dc;

import java.util.*;

import javax.swing.tree.MutableTreeNode;

public class GepKExpression {
	private ArrayList<GPObject> kExpression;
	private int head;
	private int tail;
	private int size;
	
	public void randomInit() {
		

	}
	public int getSize()
	{
		return size;
	}
	public GPObject getGpObject(int index)
	{
		return kExpression.get(index);
	}
	
	public void setGpObject(int index, GPObject element) {
		kExpression.set(index, element);
	}
	public void add(GPObject element) {
		kExpression.add(element);
	}
	
	public GepKExpression(int size) {
		kExpression = new ArrayList<GPObject>();
		this.size = size;
		this.head = (size-1)/2;
		this.tail= size-this.head;	
	}
	
	public void randomInit(ArrayList<GPObject> operatorSet, ArrayList<GPObject> terminalSet) {
		Random R = new Random();
		int i;
		this.kExpression.clear();
		for (i=0;i<this.head;i++) {
			int index = R.nextInt(operatorSet.size()+terminalSet.size());
			if ( index<operatorSet.size() ) {
				this.kExpression.add(operatorSet.get(index));
			} else {
				this.kExpression.add(terminalSet.get(index-operatorSet.size()));
			}
		}
		for (;i<this.size;i++) {
			int index = R.nextInt(terminalSet.size());
			this.kExpression.add(terminalSet.get(index));
		}
	}
	
	public GepExpressionTree getExpressionTree() {
		ArrayList<GepExpressionNode> reserve =new ArrayList<GepExpressionNode>();
		GepExpressionNode root = new GepExpressionNode();
		reserve.add(root);
		Iterator<GPObject> itr = kExpression.iterator();
		while ( !(reserve.isEmpty()) ) {
			GepExpressionNode current= reserve.remove(0);
			GPObject element = itr.next();
			current.setUserObject(element);
			if ( element.isOperator() ) {
				GepExpressionNode  child1 = new GepExpressionNode();
				current.add(child1);
				reserve.add(child1);
				if ( ((GPOperator)element).isBinaryOperator()) {
					GepExpressionNode  child2 = new GepExpressionNode();
					current.add(child2);
					reserve.add(child2);
				}
			}
		}
		return new GepExpressionTree(root);
	}
	
	public String toString() {
		String s = "";
		Iterator<GPObject> itr = kExpression.iterator();
		while (itr.hasNext()) {
			GPObject element = (GPObject) itr.next();
			s += "["+element.toString()+"]";
		}
		return s;	
	}
}
