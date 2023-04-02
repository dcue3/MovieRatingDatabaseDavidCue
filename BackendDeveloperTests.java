import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BackendDeveloperTests {
    // Creating instances of the Objects required to construct a MovieRanking as well instantiating a MovieRanking Object
	protected RedBlackTreeBD<MovieInterface> RBTInstance = null;
	protected MovieReaderBD MovieReaderInstance = null;
	protected MovieRanking instance = null;
    // Initializing the Objects and using them to Initialize the MovieRanking and using BeforeEach to do this before each test
    @BeforeEach
    public void createInstances() {
    	RBTInstance = new RedBlackTreeBD<MovieInterface>();
    	MovieReaderInstance = new MovieReaderBD();
        instance = new MovieRanking(MovieReaderInstance, RBTInstance);
    }

    /**
     * This tests the proper functionality of the loadMoviesList() method of the Backend.
     * 
     */
	@Test
	void loadMoviesListTest() {
		// Testing where the loadMoviesList() is expected to not throw an Exception
		try {
			instance.loadMoviesList("ShouldWork"); 
		} catch (Exception e) {
			// Failing the test if an unexpected Exception was thrown
			fail("Unexpected Exception was thrown" + e);
		}
		
		// Testing where loadMoviesList() is expected to throw an Exception
		try {
			instance.loadMoviesList("ShouldNotWork");
			// Failing the test if an expected Exception was not thrown
			fail("Exception was expected but not thrown");
		} catch (Exception e) {
		}
		
		
	}
	
	/**
	 * Tests correctness of getMoviesByRange() and removeByRatingRange() methods of the Backend.
	 * Testing where removeByRatingRange() is expected to not throw an Exception
	 * The removeByRatingRange() makes use of the getMoviesByRating() method, thus by testing
	 * removeByRatingRange() it is also testing getMoviesByRating(). Then, The method calls 
	 * getMoviesByRating() and removes all movies in list returned by the call with RBT's remove(). 
	 * By hardcoding, a valid range (positive and first value is less than second value), 
	 * returns a list with 1 movie that has a rating > 5.0. Again by hardcoding, calling the remove 
	 * method on this 1 movie will use a hardcoded compareTo() from Movie class, result in no
	 * exception to be thrown by the remove method. On the contrary, when range is not valid,
	 * getMoviesByRating() called by removeByRatingRange() would result in a list with 1 movie with
	 * a rating < 5.0; calling remove on this results in a NullPointerException being thrown by 
	 * remove() by hardcoding that into the remove() method in RedBlackTreeBD.
	 */
	@Test
	void getMoviesByRangeAndRemoveByRatingRangeTest() {
		// First calling with a valid range (should not result in an Exception being thrown)
		try {
			instance.removeByRatingRange(4.0, 6.0);
		} catch (Exception e) {
			fail("Unexpected Exception was thrown");
		}
		// Calling with invalid range (should result in an Exception, fail if Exception not thrown)
		try {
			instance.removeByRatingRange(-8.0, -6.0);
			// Failing the test if an unexpected Exception was thrown
			fail("Expected Exception was not thrown");
		} catch (Exception e) {
		}
	}
	
	/**
	 * This tests the proper functionality of the printAllMovies() method of MovieRanking.
	 */
	@Test
	void printAllMoviesTest() {
		// Making sure the returned String is equal to the  expected String which is the hardcoded String
		try {
			String returned = instance.printAllMovies();
			String expected = "		--------------------- Movie Ranker Movies ---------------------"
					+ "\n\n1. \"Hardcoded expected title movie 1\", Rating: 10.0, Director: Test1,"
					+ " Run Time: 0, Year: 0\n"
					+ "2. \"Hardcoded expected title movie 2\", Rating: 9.6, Director: Test2,"
					+ " Run Time: 0, Year: 0\n"
					+ "3. \"Hardcoded expected title movie 3\", Rating: 8.0, Director: Test3,"
					+ " Run Time: 0, Year: 0\n"
					+ "4. \"Hardcoded expected title movie 4\", Rating: 4.0, Director: Test4, "
					+ "Run Time: 0, Year: 0\n";
			assertEquals(returned, expected);
		} catch (Exception e) {
			// Failing the test if an unexpected Exception was thrown
			fail("Unexpected Exception was thrown");
		}
	}
	
	/**
	 * This tests the proper functionality of the getDataTest() method of MovieRanking. 
	 */
	@Test
	void getDataTest() {
		try {
			// Not adding a Movie to the Movie Ranker to make sure size is 0 and no movies in ranker
			// Message is returned by the method
			String returned = instance.getData();
			String expected = "The current Movie Ranking is empty, please add movies or load in a "
					+ "file of movies to see statistics.";
			assertEquals(returned, expected);
		} catch (Exception e) {
			// Failing the test if an unexpected Exception was thrown
			fail("Unexpected Exception was thrown");
		}
		try {
			// Adding a Movie so size is not 0 and the no movies String is not returned
			instance.addMovie(null, 0, null, 0, 0);
			String returned = instance.getData();
			String expected = "--------------------- Movie Ranker Statistics "
					+ "---------------------\n\n"
					+ "There are currently 4 movie(s) in this MovieRanking\n\n"
					+ "The average rating across all movies is: 7.9\n\n"
					+ "The highestrated movie is: \"Hardcoded expected title movie 1\"\n"
					+ "    Rating: 10.0\n"
					+ "    Director: Test1\n"
					+ "    Run Time: 0\n"
					+ "    Year: 0\n\n"
					+ "The lowest rated movie is: \"Hardcoded expected title movie 4\"\n"
					+ "    Rating: 4.0\n"
					+ "    Director: Test4\n"
					+ "    Run Time: 0\n"
					+ "    Year: 0";
			assertEquals(returned, expected);
		} catch (Exception e) {
			// Failing the test if an unexpected Exception was thrown
			fail("Unexpected Exception was thrown");
		}
	}
	
	/**
	 * This tests that the addMovieTest() method of MovieRanking class works as expected
	 */
	@Test
	void addMovieTest() {
		// Attempting to add a movie and checking that it is correctly inserted as the root with assertEquals
		try {
			instance.addMovie("test movie 1", 10.0, null, 0, 0);
			assertEquals(RBTInstance.root.data.compareTo(10.0), 0);
			assertEquals(RBTInstance.root.data.compareTo(9.0), 1);
			assertEquals(RBTInstance.root.data.compareTo(11.0), -1);
		} catch (Exception e){
			// Failing the test if an unexpected Exception was thrown
			fail ("Unexpected Exception was thrown");
		}
	}

}
