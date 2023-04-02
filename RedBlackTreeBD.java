import java.util.ArrayList;
import java.util.List;

public class RedBlackTreeBD<Value extends Comparable<Value>> implements RedBlackTreeInterface<Value> {

	/**
     * This class represents a node holding a single value within a binary tree.
     */
    protected static class Node<T> {
    	public int blackHeight;
        public T data;
        // The context array stores the context of the node in the tree:
        // - context[0] is the parent reference of the node,
        // - context[1] is the left child reference of the node,
        // - context[2] is the right child reference of the node.
        // The @SupressWarning("unchecked") annotation is used to supress an unchecked
        // cast warning. Java only allows us to instantiate arrays without generic
        // type parameters, so we use this cast here to avoid future casts of the
        // node type's data field.
        @SuppressWarnings("unchecked")
        public Node<T>[] context = (Node<T>[])new Node[3];
        public Node(T data) { 
        	this.data = data; 
        	this.blackHeight = 0;
        }
        
        /**
         * @return true when this node has a parent and is the right child of
         * that parent, otherwise return false
         */
        public boolean isRightChild() {
            return context[0] != null && context[0].context[2] == this;
        }

    }

    protected Node<Value> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs operations to ensure tree does not violate RBT properties after an insertion
     * @param addedNode is the inserted node
     */
    public void enforceRBTreePropertiesAfterInsert(Node<Value> addedNode) {
    	if ((addedNode == this.root) || (addedNode.context[0] == root)) {
    		this.root.blackHeight = 1;
    		return;
    	}
    	if (addedNode.context[0].blackHeight != 0) {
    		return;
    	}    	
    	// UNCLE IS RIGHT
    	if (addedNode.context[0].context[0].context[2] != addedNode.context[0]) {
    		Node<Value> uncle = addedNode.context[0].context[0].context[2];
    		if ((uncle == null) || (uncle.blackHeight == 1)) {
    			if ((addedNode.context[0].context[1] == addedNode) && (addedNode.context[0].isRightChild())) {
    				rotate(addedNode, addedNode.context[0]);
    				addedNode = addedNode.context[2];
    			}
    			if ((addedNode.isRightChild()) && (addedNode.context[0].context[0].context[1] == addedNode.context[0])) {
    				rotate(addedNode, addedNode.context[0]);
    				addedNode = addedNode.context[1];
    			}
    		}
    		
    		if ((uncle == null) || (uncle.blackHeight == 1)) {
    			int blackHeightParent = addedNode.context[0].blackHeight;
    			addedNode.context[0].blackHeight = addedNode.context[0].context[0].blackHeight;
    			addedNode.context[0].context[0].blackHeight = blackHeightParent;
    			rotate(addedNode.context[0], addedNode.context[0].context[0]);
    		}
        	else if (uncle.blackHeight == 0) {	
        		addedNode.context[0].blackHeight = 1;
        		addedNode.context[0].context[0].blackHeight = 0;
        		addedNode.context[0].context[0].context[2].blackHeight = 1;
        		enforceRBTreePropertiesAfterInsert(addedNode.context[0].context[0]);
        	} 
    	}
    	
    	// UNCLE IS LEFT
    	else if (addedNode.context[0].context[0].context[1] != addedNode.context[0]) {
    		Node<Value> uncle = addedNode.context[0].context[0].context[1];
    		if ((uncle == null) || (uncle.blackHeight == 1)){
    			if ((addedNode.context[0].context[1] == addedNode) && (addedNode.context[0].isRightChild())) {
    				rotate(addedNode, addedNode.context[0]);
    				addedNode = addedNode.context[2];
    			}
    			if ((addedNode.isRightChild()) && (addedNode.context[0].context[0].context[1] == addedNode.context[0])) {
    				rotate(addedNode, addedNode.context[0]);
    				addedNode = addedNode.context[1];
    			}
    		}
    		
    		if ((uncle == null) || (uncle.blackHeight == 1)) {
    			int blackHeightParent = addedNode.context[0].blackHeight;
    			addedNode.context[0].blackHeight = addedNode.context[0].context[0].blackHeight;
    			addedNode.context[0].context[0].blackHeight = blackHeightParent;
    			rotate(addedNode.context[0], addedNode.context[0].context[0]);
    		}
    		else if (uncle.blackHeight == 0) {
    			addedNode.context[0].blackHeight = 1;
    			addedNode.context[0].context[0].blackHeight = 0;
    			addedNode.context[0].context[0].context[1].blackHeight = 1;
    			enforceRBTreePropertiesAfterInsert(addedNode.context[0].context[0]);
    		}
    	}    	
    	this.root.blackHeight = 1;
    }
    
    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is already contained in the tree
     */
    @Override
    public boolean insert(Value data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<Value> newNode = new Node<>(data);
        if (this.root == null) {
            // add first node to an empty tree
            root = newNode; size++; 
            enforceRBTreePropertiesAfterInsert(newNode);
            return true;
        } else {
            // insert into subtree
            Node<Value> current = this.root;
            while (true) {
                int compare = newNode.data.compareTo(current.data);
                if (compare == 0) {
                    throw new IllegalArgumentException("This RedBlackTree already contains value " + data.toString());
                } else if (compare < 0) {
                    // insert in left subtree
                    if (current.context[1] == null) {
                        // empty space to insert into
                        current.context[1] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newNode);
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[1];
                    }
                } else {
                    // insert in right subtree
                    if (current.context[2] == null) {
                        // empty space to insert into
                        current.context[2] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newNode);
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[2]; 
                    }
                }
            }
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * right child of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<Value> child, Node<Value> parent) throws IllegalArgumentException {
        // TODO: Implement this method.
    	// Throwing exceptions when child and parent aren't directly related or either is null
    	if (parent == null || child == null) {
    		throw new IllegalArgumentException();
    	}
    	if (parent.context[1] == null && parent.context[2] == null) {
    	    throw new IllegalArgumentException();
    	}
    	if ((parent.context[1] == null || !(parent.context[1].equals(child)))
    			&& !(parent.context[2].equals(child))) {
    		throw new IllegalArgumentException();
    	}
    	if ((parent.context[2] == null || !(parent.context[2].equals(child)))
    			&& !(parent.context[1].equals(child))) {
    		throw new IllegalArgumentException();
    	}
    	if (child.context[0] == null || !(child.context[0].equals(parent))) {
    		throw new IllegalArgumentException();
    	}
    	// If the child is a right child, then do a left rotation, and if the parent is the root,
    	// make sure the child becomes the new root
    	if (child.isRightChild()) {
    		// If the parent is the root, make the child the new root
    	    if (parent.context[0] == null) {
    		    child.context[0] = null;
    		    this.root = child;
      	    }
    	    // Else if the parent is not the root, adjust grandparent's children and child's parent
    	    else if (parent.isRightChild()) {
    	        parent.context[0].context[2] = child;
    	        child.context[0] = parent.context[0];
    	    }
    	    else {
      	        parent.context[0].context[1] = child;
      	        child.context[0] = parent.context[0];
      	    }
    	    // Finally, make child the new parent, child's old left child parent's new right child,
    	    // and make child's new left child parent
    	    parent.context[0] = child;
    	    parent.context[2] = child.context[1];
    	    child.context[1] = parent;
    	} 
    	// Else, the child is a left child, so do a right rotation, and if the parent is the root,
    	// make sure the child becomes the new root
    	else {
    		// If the parent is the root, make the child the new root
    	    if (parent.context[0] == null) {
    		    child.context[0] = null;
    		    this.root = child;
      	    }
    	    // Else if the parent is not the root, adjust grandparent's children and child's parent
    	    else if (parent.isRightChild()) {
    		    parent.context[0].context[2] = child;
    		    child.context[0] = parent.context[0];
    	    }
    	    else {
    	    	parent.context[0].context[1] = child;
    	    	child.context[0] = parent.context[0];
    	    }
    	    // Finally, make child the new parent, child's old right child parent's new left child,
    	    // and make child's new right child parent
    	    parent.context[0] = child;
    	    parent.context[1] = child.context[2];
    	    child.context[2] = parent;
    	}
    }


	@Override
	/**
     * Removes the value data from the tree if the tree contains the value.
     * This method will not attempt to rebalance the tree after the removal and
     * should be updated once the tree uses Red-Black Tree insertion.
     * @return true if the value was remove, false if it didn't exist
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is not stored in the tree
     */
    public boolean remove(Value data) throws NullPointerException, IllegalArgumentException {
		// Remove is called by removeByRatingRange() in Backend, which gets the movies in the
		// specified range using the AE's getByRange() method, which has been hardcoded.
		// This test will make sure the remove() is getting the right Value data by looking for the 
		// expected hardcoded line.
		
		// Creating a movie with rating 5.0 and checking that a valid range will return a movie with
		// Rating > 5.0 as defined in the getByRange() method below
		MovieInterface five = new MovieBD(null, 0, 0, 5.0, null);
		if (!(data.compareTo((Value) (five)) > 0)) {
			throw new IllegalArgumentException();
		}
		else {
			return true;
		}
    }

	@Override
	public boolean contains(Value data) {
		return true;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public List<Value> getByRange(Value y, Value x) {
		// Creating a fake ArrayList to return, adding one String object, which is comparable
		ArrayList<Value> toReturn = new ArrayList<Value>();
		// Returning a message indicating a valid range if passed a valid range
		MovieInterface valid = new MovieBD(null, 0, 0, 0.0, null);
		if ((x.compareTo((Value)valid) >= 0) && (y.compareTo((Value)valid) >= 0)) {
			if (x.compareTo(y) > 0) {
				// Creating a new movie with rating > 5.0 to indicate a valid range
				MovieInterface validReturn = new MovieBD(null, 0, 0, 8.0, null);
				toReturn.add((Value) validReturn);
				return toReturn;
			}
		}
		// Else, returning a movie with rating < 5.0 indicating an invalid range
		MovieInterface inValid = new MovieBD(null, 0, 0, 4.0, null);
		toReturn.add((Value) inValid);
		return toReturn;
	}

	@Override
	public List<Value> inOrderTraversal() {
		// Creating a fake ArrayList to return, adding one String object, which is comparable
		ArrayList<Value> toReturn = new ArrayList<Value>();
		// Creating a fake movie with an expected movieTitle and rating
		toReturn.add((Value) new MovieBD("Hardcoded expected title movie 4", 0, 0, 4.0, "Test4"));
		// Creating a second fake movie with an expected movieTitle and rating
		toReturn.add((Value) new MovieBD("Hardcoded expected title movie 3", 0, 0, 8.0, "Test3"));
		// Creating a third fake movie with an expected movieTitle and rating
		toReturn.add((Value) new MovieBD("Hardcoded expected title movie 2", 0, 0, 9.6, "Test2"));
		// Creating a fourth fake movie with an expected movieTitle and rating
		toReturn.add((Value) new MovieBD("Hardcoded expected title movie 1", 0, 0, 10.0, "Test1"));
		return toReturn;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.root = null;
		
	}

	@Override
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		return false;
	}

}
