import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**x
 *
 *
 * This class builds on top of the RBT implementation.
 *
 * @author AlgorithmEngineer, Shreya Godishala
 */
public class RedBlackTreeAE<Value extends Comparable<Value>> extends RedBlackTree<Value> implements RedBlackTreeInterfaceAE<Value> {

    // The context array stores the context of the node in the tree:
    // - context[0] is the parent reference of the node,
    // - context[1] is the left child reference of the node,
    // - context[2] is the right child reference of the node.
    // The @SupressWarning("unchecked") annotation is used to supress an unchecked
    // cast warning. Java only allows us to instantiate arrays without generic
    // type parameters, so we use this cast here to avoid future casts of the
    // node type's data field.


    // additional properties and methods specific to RedBlackTreeAE class
    // public RedBlackTree()

    // Call RBT and enforce
    // call Remove from RBT
    @Override
    public boolean remove(Value data) throws NullPointerException, IllegalArgumentException{
        Node<Value> tempNode = new Node(data);
        super.remove(data);
        recolor(tempNode);
        return true;
    }

    // Call rotate form RBT

    // need to call enforce
    public void recolor(Node<Value> deletedNode) {
            Node<Value> sibling;
            Node<Value> parent;
            boolean isLeftChildDeleted = deletedNode.isLeftChild();

            while (deletedNode != root && (deletedNode.blackHeight == 0) ) {
                parent = deletedNode.context[0]; // getParent
                if (isLeftChildDeleted) {
                    sibling = parent.context[2];

                    // Case 1: The sibling is red
                    if (sibling.blackHeight == 1 ) {
                        sibling.blackHeight = 0; // set black
                        parent.blackHeight = 1; // set to red
                        rotate(deletedNode, parent);
                        sibling = parent.context[2]; // right child
                    }

                    // Case 2: Both the sibling and its children are black
                    if ((sibling.context[1].blackHeight == 0) && (sibling.context[2].blackHeight == 0)) {
                        sibling.blackHeight = 1;
                        deletedNode = parent;
                        isLeftChildDeleted = deletedNode.isLeftChild();
                    } else {
                        // Case 3: The sibling is black and its left child is red
                        if (sibling.context[2].blackHeight == 0) {
                            sibling.context[1].blackHeight = 0; // left child set to black
                            sibling.blackHeight = 1 ; // set to red
                            rotate(deletedNode, sibling);
                            sibling = parent.context[2];
                        }

                        // Case 4: The sibling is black and its right child is red
                        sibling.blackHeight = parent.blackHeight;
                        parent.blackHeight = 0; // set to black
                        sibling.context[2].blackHeight = 0;
                        rotate(deletedNode, parent);
                        deletedNode = root;
                    }
                } else {
                    sibling = parent.context[1];

                    // Case 1: The sibling is red
                    if (sibling.blackHeight == 1) {
                        sibling.blackHeight = 0;
                        parent.blackHeight = 1;
                        rotate(deletedNode, parent);
                        sibling = parent.context[1];
                    }

                    // Case 2: Both the sibling and its children are black
                    if (sibling.context[2].blackHeight == 0 && sibling.context[1].blackHeight == 0) {
                        sibling.blackHeight = 1;
                        deletedNode = parent;
                        isLeftChildDeleted = deletedNode.isLeftChild();
                    } else {
                        // Case 3: The sibling is black and its right child is red
                        if (sibling.context[1].blackHeight == 0) {
                            sibling.context[2].blackHeight = 0;
                            sibling.blackHeight = 1;
                            rotate(deletedNode, sibling);
                            sibling = parent.context[1];
                        }

                        // Case 4: The sibling is black and its left child is red
                        sibling.blackHeight = (parent.blackHeight);
                        parent.blackHeight = 0;
                        sibling.context[1].blackHeight = 0;
                        rotate(deletedNode, parent);
                        deletedNode = root;
                    }
                }
            }
            deletedNode.blackHeight = 0;
        }


    //make this method
    public List<Value> getByRange(double y, double x) {
        //getByRange
        List<Value> movies = new ArrayList<>();
        getByRangeHelper(root, y,  x, movies);
        return movies;
    }

    private void getByRangeHelper(Node node, double y, double x, List<Value> movies){
        if (node == null) {
            return;
        }
        Value dataAdd = (Value) node.data;
        double dataHolder = (double) node.data;

        // If the range overlaps with the subtree rooted at the current node,
        // add the node's data to the list of movies
        if (dataHolder >= y && dataHolder <= x) {
            movies.add(dataAdd);
        }



        // If the left subtree has values in the desired range, recursively
        // search that subtree
        if (node.context[1] != null && (dataHolder > y || (double) node.context[1].data >= y)) {
            getByRangeHelper(node.context[1], y, x, movies);
        }

        // If the right subtree has values in the desired range, recursively
        // search that subtree
        if (node.context[2] != null && (dataHolder < x || (double) node.context[2].data <= x)) {
            getByRangeHelper(node.context[2], y, x, movies);
        }
    }



        public List<Value> inOrderTraversal() {
            List<Value> traversal = new ArrayList<>();
            inOrderTraversalHelper(this.root, traversal);
            return traversal;
        }

    @Override
    public void clear() {

    }

    private void inOrderTraversalHelper(Node<Value> node, List<Value> traversal) {
            if (node != null) {
                inOrderTraversalHelper(node.context[1], traversal); // traverse left subtree
                traversal.add(node.data); // visit current node
                inOrderTraversalHelper(node.context[2], traversal); // traverse right subtree
            }
        }
     //   sb.append(" ]");
      //  return sb.toString();

    }

    // call clear from RBT
