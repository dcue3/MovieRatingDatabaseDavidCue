import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.assertThat;

public class AlgorithmEngineerTests extends RedBlackTreeAE {

    @Test
    void testRecolor() {
        // Case 1: Examined node is root, end of recursion
        // create binary tree
        List<Integer> listTrack = new ArrayList<>();


        RedBlackTreeAE<Integer> tree1 = new RedBlackTreeAE<Integer>();
        // set up the tree
        tree1.insert(23);
        tree1.insert(7);
        tree1.insert(41);
        tree1.insert(5);
        // inserting the new node
        tree1.insert(37);

        System.out.println(tree1.inOrderTraversal());


        //remove
        listTrack.add(tree1.root.blackHeight);
        listTrack.add(tree1.root.context[1].blackHeight); //left
        listTrack.add(tree1.root.context[2].blackHeight);
        listTrack.add(tree1.root.context[2].context[1].blackHeight);
        //System.out.println(listTrack);

        System.out.println("Before " + listTrack);

        tree1.remove(5);
        System.out.println(tree1.inOrderTraversal());
        listTrack.add(tree1.root.blackHeight);
        listTrack.add(tree1.root.context[1].blackHeight); //left
        listTrack.add(tree1.root.context[2].blackHeight);
        listTrack.add(tree1.root.context[2].context[1].blackHeight);


        System.out.println(listTrack);

        // check the colors of each node
        assertEquals( 1, tree1.root.blackHeight); // black is 1 and the //"Root should be black",
        assertEquals( 1, tree1.root.context[1].blackHeight); //"Left child should be black",
        // the left child in this should be black
        assertEquals( 1, tree1.root.context[2].blackHeight); //Right child should be black",
        // the right child should be black
        assertEquals( 0, tree1.root.context[2].context[1].blackHeight); //"New node should be red",
        // all the newNodes are red


    }

    @Test
void testGetByRange() {
    // create binary tree with movie release years as values
     // create binary tree with movie release years as values
        RedBlackTreeAE<Integer> tree = new RedBlackTreeAE<Integer>();
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);

        // get movies released between 2005 and 2015
        List<Integer> movies = tree.getByRange(7, 10);

        System.out.print(movies);

        // check that the correct movies are returned
        List<Integer> expected = new ArrayList<>();
        expected.add(7);
        expected.add(8);

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
/**
 * This is an integration test that combines DataWrangler, AE, and the backend to test some methods.
 */
@Test
public void IntegrationTest1() {
    // Create an instance of RedBlackTreeAE to store movies.
    RedBlackTreeInterface<MovieInterface> movieTree = new RedBlackTreeAE<MovieInterface>();

    // Create an instance of MovieReaderDW to read movie data from a file.
    MovieReaderInterface movieReader = new MovieReaderDW();

    // Create an instance of MovieRanking that uses the movie reader and the Red-Black tree to rank movies by rating.
    MovieRankingInterface movieRanker = new MovieRanking(movieReader, movieTree);

    // Load a list of movies from a file and add them to the Red-Black tree.
    try {
        movieRanker.loadMoviesList("./data/test.txt");
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    // Get a list of movies that have a rating between 6.0 and 8.0 and check if it contains a specific movie.
    String moviesByRating = movieRanker.getMoviesByRating(5.0, 9.0);
System.out.print("moviesByRating");

   String expectedMovie = "Title: Knives Out";
    assertThat(moviesByRating, containsString(expectedMovie)); //the first movie with the 8.0 rating
}



/**
 * This is an integration test that combines DataWrangler, AE, and the backend to test some methods.
 * It checks if the getMoviesByRating method returns movies with a rating between 0.0 and 5.0.
 */
@Test
public void IntegrationTest2() {
    // Create an instance of RedBlackTreeAE to store movies.
    RedBlackTreeInterface<MovieInterface> movieTree = new RedBlackTreeAE<MovieInterface>();

    // Create an instance of MovieReaderDW to read movie data from a file.
    MovieReaderInterface movieReader = new MovieReaderDW();

    // Create an instance of MovieRanking that uses the movie reader and the Red-Black tree to rank movies by rating.
    MovieRankingInterface movieRanker = new MovieRanking(movieReader, movieTree);

    // Load a list of movies from a file and add them to the Red-Black tree.
    try {
        movieRanker.loadMoviesList("./data/test.txt");
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
movieRanker.addMovie("Title: Titanic", 3.4, "Wed Anderson", 1, 2000);

    // Get a list of movies that have a rating between 0.0 and 5.0 and check if it contains a specific movie.
    String moviesByRating = movieRanker.getMoviesByRating(0.0, 5.0);
    String expectedMovie = "Title: Titanic";
    assertThat(moviesByRating, containsString(expectedMovie)); //the first movie with the 5.0 rating
}

    /**
     *Junit test for codereview of Backend methods
     *testing the loadData method and getData() method.
     */
    @Test
    public void CodeReviewofBackendDeveloper(){
        // Create an instance of RedBlackTreeAE to store movies.
        RedBlackTreeInterface<MovieInterface> movieTree = new RedBlackTreeAE<MovieInterface>();

        // Create an instance of MovieReaderDW to read movie data from a file.
        MovieReaderInterface movieReader = new MovieReaderDW();

        // Create an instance of MovieRanking that uses the movie reader and the Red-Black tree to rank movies by rating.
        MovieRankingInterface movieRanker = new MovieRanking(movieReader, movieTree);

        // Load a list of movies from a file and add them to the Red-Black tree.
        try {
            movieRanker.loadMoviesList("./data/test.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertThat(movieRanker.getData(), containsString("Director: Todd Haynes"));

    }
/**
 * test is used to test the readMoviesFromFile method of the MoviesReaderDW class, 
 * and also to test the getTitle() and getRating() methods of the MovieDW class.
 */
@Test
public void testReadMoviesFromFile() {
    // Declare variables
    List<MovieInterface> movies = null;
    MovieReaderInterface movieReader = new MovieReaderDW();
        try {
        // Call the readMoviesFromFile method to read movie data from a test file
        movies = movieReader.readMoviesFromFile("./data/test.txt");
    } catch (FileNotFoundException e) {
        // If the file is not found, print the stack trace
        e.printStackTrace();
    }

    // Check if the readMoviesFromFile method correctly read all the data from the test.txt file
    assertEquals(1000, movies.size()); // The test file has 1000 movies

    // Check if the title of the first movie in the list matches the expected title
    assertEquals("Pulp Fiction", movies.get(6).getTitle());

    // Check if the rating of the first movie in the list matches the expected rating
    assertEquals(Double.valueOf(8.9), movies.get(6).getRating());


}
}
