import java.util.List;

public interface RedBlackTreeInterface<Value extends Comparable<Value>> extends SortedCollectionInterface<Value> {
    // public RedBlackTree();
	// remove from RBT
	// insert call from RBT 
	// call rotate from RBT 
	// call enforceRBTreePropertiesAfterInsert from RBT 
	// public void recolorAfterDelete (Node<Value> node); // this should be called when enforce RBT after delete 
	// Methods below, are the new methods added by the AE in addition to extending SortedCollectionInterface
	public List<Value> getByRange(double y, double x); // this should get the Nodes that fall within the lower and upper bounds of y and x) 
	public List<Value> inOrderTraversal(); // calling the inOrderString but not as the String 
	  public void clear(); // call from RBT 

}
