import java.util.ArrayList;
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

       //old code // Node<Value> tempNode = new Node(data);
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<Value> nodeWithData = this.findNodeWithData(data);
            // throw exception if node with data does not exist
            if (nodeWithData == null) {

                throw new IllegalArgumentException("The following value is not in the tree and cannot be deleted: " + data.toString());
            }

            boolean hasRightChild = (nodeWithData.context[2] != null);
            boolean hasLeftChild = (nodeWithData.context[1] != null);
            if (hasRightChild && hasLeftChild) {
                // has 2 children
                Node<Value> successorNode = this.findMinOfRightSubtree(nodeWithData);
                // replace value of node with value of successor node
                nodeWithData.data = successorNode.data;
                // remove successor node
                if (successorNode.context[2] == null) {
                    // successor has no children, replace with null
                    this.replaceNode(successorNode, null);
                } else {
                    // successor has a right child, replace successor with its child
                    this.replaceNode(successorNode, successorNode.context[2]);
                }
            } else if (hasRightChild) {
                // only right child, replace with right child
                this.replaceNode(nodeWithData, nodeWithData.context[2]);
            } else if (hasLeftChild) {
                // only left child, replace with left child
                this.replaceNode(nodeWithData, nodeWithData.context[1]);
            } else {
                // no children, replace node with a null node
                this.replaceNode(nodeWithData, null);
                this.size--;
                return true;
            }
            this.size--;
            recolor(nodeWithData);
            return true;
        }
    }
    private void recolor(Node<Value> node) {
        // Case 1: Examined node is root, end of recursion
        if (node == root) {
            node.blackHeight = 0; // color is black
            return;
        }

        Node sibling = getSibling(node);

        if(sibling == null){
            return;
        }
        // Case 2: Red sibling
        if (sibling.blackHeight == 1) { // if sibling is red
            handleRedSibling(node, sibling);
            sibling = getSibling(node); // Get new sibling for fall-through to cases
        }

        // Cases 3+4: Black sibling with two black children
        if ((sibling.context[1].blackHeight == 0) && (sibling.context[2].blackHeight == 0)) {
            sibling.blackHeight = 1;

            // Case 3: Black sibling with two black children + red parent
            if (node.context[0].blackHeight == 1) {
                node.context[0].blackHeight = 0;
            }
            // Case 4: Black sibling with two black children + black parent
            else {
                recolor(node.context[0]); // recursively call method again
            }
        }
        // Case 5+6: Black sibling with at least one red child
        else {
            handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
        }
    }

    private Node getSibling(Node node) {
        Node parent = node.context[0]; // node parent is set to parent
        if (node == parent.context[1]) { // if node is left
            return parent.context[2]; // return parent right
        } else if (node == parent.context[2]) { // is parent is right
            return parent.context[1]; //return parent left
        } else {
            return null;
           // throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }
    private void handleRedSibling(Node node, Node sibling) {
        // Recolor
        sibling.blackHeight = 0;
        node.context[0].blackHeight = 1;

        //  and rotate
        if (node == node.context[0].context[1]) {
            rotateLeft(node.context[0]);
        } else {
            rotateRight(node.context[0]);
        }
    }
    private void handleBlackSiblingWithAtLeastOneRedChild(Node node, Node sibling) {
        boolean nodeIsLeftChild = node == node.context[0].context[1];

        // Case 5: Black sibling with at least one red child + "outer nephew" is black
        // --> Recolor sibling and its child, and rotate around sibling
        if (nodeIsLeftChild && sibling.context[2].blackHeight == 0 ) {
            sibling.context[1].blackHeight = 0;
            sibling.blackHeight = 1;
            rotateRight(sibling);
            sibling = node.context[0].context[2];
        } else if (!nodeIsLeftChild && sibling.context[1].blackHeight == 0) {
            sibling.context[2].blackHeight = 0;
            sibling.blackHeight = 1;
            rotateLeft(sibling);
            sibling = node.context[0].context[1];
        }

        // Fall-through to case 6...

        // Case 6: Black sibling with at least one red child + "outer nephew" is red
        // --> Recolor sibling + parent + sibling's child, and rotate around parent
        sibling.blackHeight = node.context[0].blackHeight;
        node.context[0].blackHeight = 0;
        if (nodeIsLeftChild) {
            sibling.context[2].blackHeight = 0;
            rotateLeft(node.context[0]);
        } else {
            sibling.context[1].blackHeight = 0;
            rotateRight(node.context[0]);
        }
    }
    private void rotateLeft(Node node) {
        Node parent = node.context[0];
        Node rightChild = node.context[2];

        node.context[2] = rightChild.context[1];
        if (rightChild.context[1] != null) {
            rightChild.context[1].context[0] = node;
        }

        rightChild.context[1] = node;
        node.context[0] = rightChild;

        replaceParentsChild(parent, node, rightChild);
    }
    private void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.context[1] == oldChild) {
            parent.context[1] = newChild;
        } else if (parent.context[2] == oldChild) {
            parent.context[2] = newChild;
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.context[0] = parent;
        }
    }
    private void rotateRight(Node node) {
        Node parent = node.context[0];
        Node leftChild = node.context[1];

        node.context[1] = leftChild.context[2];
        if (leftChild.context[2] != null) {
            leftChild.context[2].context[0] = node;
        }

        leftChild.context[2] = node;
        node.context[0] = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }
    // Call rotate form RBT

    // need to call enforce

    //make this method
    public List<Value> getByRange(Value y, Value x) {
        //getByRange
        List<Value> movies = new ArrayList<>(); // have my list of movies
        getByRangeHelper(root, y,  x, movies); // call my helper class
        return movies;
    }

    private void getByRangeHelper(Node<Value> node, Value y, Value x, List<Value> movies){
        if (node == null) { // check if my node is null which is my root node
            return;
        }
       // Value dataAdd = (Value) node.data;
        if (node.data.compareTo(y) >=0 && node.data.compareTo(x) <= 0) { // want to check if the node data value is in range
            movies.add(node.data);
        }
        getByRangeHelper(node.context[1], y, x, movies); // if not recurse left
        getByRangeHelper(node.context[2], y, x, movies);// recurse right
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
