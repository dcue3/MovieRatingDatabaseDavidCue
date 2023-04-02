import java.util.List;

public interface RedBlackTreeInterface<Value extends Comparable<Value>> extends SortedCollectionInterface<Value> {
    // public RedBlackTree();
	public boolean remove(Value data);
	public boolean insert(Value data); 
	// public void rotate(Node<Value> node); 
	// public void recolor(Node<Value> node);  
	public List<Value> getByRange(Value y, Value x); // this should get the Nodes that fall within the lower and upper bounds of y and x) 
	public List<Value> inOrderTraversal(); // calling the inOrderString but not as the String 
	public void clear(); // call from RBT 

}
