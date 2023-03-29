import java.util.List;

/**
 * This is the interface for my project  
 */

public interface RedBlackTreeInterfaceAE<Value extends Comparable<Value>>   {
    // public RedBlackTree()
    public boolean remove(Value data); // returns the data of the node removed
    public boolean insert(Value data);
   // public void rotate(Node<Value> node);
    //public void recolor(Node<Value> node);
    public List<Value> getByRange(double y, double x);
    public List<Value> inOrderTraversal();
    public void clear();
}
