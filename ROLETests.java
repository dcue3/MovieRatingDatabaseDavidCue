import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class RBTTesting extends RedBlackTreeAE {

    @Test
    void testRecolor() {
        // create binary tree
        RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
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
        RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);

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
        RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        // perform in-order traversal
        List<Integer> traversal = tree.inOrderTraversal();

        // check that nodes are visited in the correct order
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(8);
        assertEquals(traversal, expected);
    }

@Test
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

@Test
/**
 * Black Uncle opposite child side tree test
 * Tests inserting a new node I'm trying to test the first gradescope error
 */
    public void test2() {
        RedBlackTree<Integer> tree2 = new RedBlackTree<Integer>();
        // set up the tree
        tree2.insert(45);
        tree2.insert(26 );
        tree2.insert(17);

        // inserting the new node
        tree2.insert(18);

        // check the colors of each node
        assertEquals("Root should be 45", "45", tree2.toLevelOrderString().substring(0,2)); // black is 1 and the
        assertEquals("Left child should be 26", "26", tree2.toLevelOrderString().substring(3,5) );

        assertEquals("Right child shoud be 72", "72", tree2.toLevelOrderString().substring(6, 8));
        // the right child should be black
        assertEquals("Left child of 26 should be 18", "18", tree2.toLevelOrderString().substring(9, 11));
        // all the newNodes are red

    }

    @Test
    /**
     * Black Uncle opposite child side tree test
     * Tests inserting a new node I'm trying to test the first gradescope error
     */
    public void test3() {
        RedBlackTree<Integer> tree3 = new RedBlackTree<Integer>();
        // set up the tree
        tree3.insert(45);
        tree3.insert(26 );
        tree3.insert(17);

        // inserting the new node
        tree3.insert(18);

        // check the colors of each node
        assertEquals("Root should be 45", "45", tree3.toLevelOrderString().substring(0,2)); // black is 1 and the
        assertEquals("Left child should be 26", "26", tree3.toLevelOrderString().substring(3,5) );

        assertEquals("Right child shoud be 72", "72", tree3.toLevelOrderString().substring(6, 8));
        // the right child should be black
        assertEquals("Left child of 26 should be 18", "18", tree3.toLevelOrderString().substring(9, 11));
        // all the newNodes are red
    }

}
