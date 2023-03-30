import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class ROLETests extends RedBlackTreeAE {

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
}
