import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class AlgorithmEngineerTests extends RedBlackTreeAE {

    @Test
    void testRecolor() {
        // create binary tree
        RedBlackTreeAE<Integer> tree = new RedBlackTreeAE<Integer>();
       // BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        // delete node with value 2
        tree.remove(2);

        // check that black height is restored
        assertEquals(tree.root.blackHeight, 1);
        assertEquals(tree.root.context[1].blackHeight, 0);
        assertEquals(tree.root.context[2].blackHeight, 0);
    }

    @Test
void testGetByRange() {
    // create binary tree with movie release years as values
    RedBlackTreeAE<Integer> tree = new RedBlackTreeAE<Integer>();
    tree.insert(2004);
    tree.insert(2005);
    tree.insert(2006);
    tree.insert(2010);
    tree.insert(2015);

    // get movies released between 2005 and 2015
    List<Integer> movies = tree.getByRange(2005, 2015);

    // check that the correct movies are returned
    List<Integer> expected = new ArrayList<>();
    expected.add(2010);
    expected.add(2015);
    expected.add(2005);
    assertEquals(movies, expected);
}

    @Test
    void testInOrderTraversal() {
        // create binary tree with integer values
        RedBlackTreeAE<Integer> tree = new RedBlackTreeAE<Integer>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        // perform in-order traversal
        //List<Integer> traversal = tree.inOrderTraversal();

        // check that nodes are visited in the correct order
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(8);
        //ssertEquals(traversal, expected);
    }

    @org.junit.Test
/**
 * Tests inserting a new node I'm trying to test the first gradescope error
 */
    public void test1() {
        RedBlackTree<Integer> tree1 = new RedBlackTree<Integer>();
        // set up the tree
        tree1.insert(23);
        tree1.insert(7);
        tree1.insert(41);

        // inserting the new node
        tree1.insert(37);

        // check the colors of each node
        assertEquals("Root should be black", 1, tree1.root.blackHeight); // black is 1 and the
        assertEquals("Left child should be black", 1, tree1.root.context[1].blackHeight);
        // the left child in this should be black
        assertEquals("Right child should be black", 1, tree1.root.context[2].blackHeight);
        // the right child should be black
        assertEquals("New node should be red", 0, tree1.root.context[2].context[1].blackHeight);
        // all the newNodes are red
    }


    @org.junit.Test
/**
 * Tests inserting a new node into a Red-Black Tree
 */
    public void testInsertion() {
        RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
        // set up the tree
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        // inserting the new node
        tree.insert(20);

        // check the colors of each node
        assertEquals("Root should be black", 1, tree.root.blackHeight);
        assertEquals("Left child should be black", 1, tree.root.context[1].blackHeight);
        assertEquals("Right child should be black", 1, tree.root.context[2].blackHeight);
        assertEquals("New node should be red", 0, tree.root.context[2].context[2].blackHeight);
    }

    @org.junit.Test
/**
 * Tests inserting nodes in descending order into the tree
 */
    public void test3() {
        RedBlackTree<Integer> tree3 = new RedBlackTree<Integer>();
        // insert nodes in descending order
        tree3.insert(41);
        tree3.insert(37);
        tree3.insert(23);
        tree3.insert(7);

        // check the colors of each node
        assertEquals("Root should be black", 1, tree3.root.blackHeight); // black is 1 and the
        assertEquals("Left child should be black", 1, tree3.root.context[1].blackHeight);
        // the left child in this should be black
        assertEquals("Right child should be black", 1, tree3.root.context[2].blackHeight);
        // the right child should be black
       // assertEquals("Right grandchild should be red", 0, tree3.root.context[2].context[1].blackHeight);
        // the right grandchild should be red

    }

}
