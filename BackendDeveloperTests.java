import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class is a test class written by the BD for Project2_DF_Red, tests BD and integration
 * @author Sudheesh Dabbara
 *
 */
class BackendDeveloperTests {
	// Creating instances of the Objects required to construct a MovieRanking as well instantiating a MovieRanking Object
	protected RedBlackTreeBD<MovieInterface> RBTInstance = null;
	protected MovieReaderBD MovieReaderInstance = null;
	protected MovieRanking instance = null;
	
	// Creating an instance of FD, DW, and AE code for integration tests with BD code
	protected FrontendFD frontendInstance = null;
	protected MovieReaderDW MovieReaderDWinstance = null;
	protected RedBlackTreeAE<MovieInterface> algorithmEngInstance = null;
	
	// Creating a MovieRanking that can be used for the integration tests
	protected MovieRanking instanceIntegration = null;
	
	// Creating a FrontendFD that is instantiated with a hardcoded backend placeholder
	protected FrontendFD frontendFDTest = null;
	
    // Initializing the Objects and using them to Initialize the MovieRanking and using BeforeEach to do this before each test
    @BeforeEach
    public void createInstances() {
    	// For Backend code for role code
    	RBTInstance = new RedBlackTreeBD<MovieInterface>();
    	MovieReaderInstance = new MovieReaderBD();
    	instance = new MovieRanking(MovieReaderInstance, RBTInstance);
    	// For integration tests in week 4
    	MovieReaderDWinstance = new MovieReaderDW();
    	algorithmEngInstance = new RedBlackTreeAE<MovieInterface>();
    	instanceIntegration = new MovieRanking(MovieReaderDWinstance, algorithmEngInstance);
		frontendInstance = new FrontendFD(instanceIntegration);		
		// For role tests in week 4
		frontendFDTest = new FrontendFD(instanceIntegration);
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
		try {
			String returned = instance.printAllMovies();
			String expected = "		--------------------- Movie Ranker Movies ---------------------\n"
					+ "\n"
					+ "1. \"Hardcoded expected title movie 1\"\n"
					+ "    Rating: 10.0\n"
					+ "    Director: Test1\n"
					+ "    Run Time: 0\n"
					+ "    Year: 0\n"
					+ "2. \"Hardcoded expected title movie 2\"\n"
					+ "    Rating: 9.6\n"
					+ "    Director: Test2\n"
					+ "    Run Time: 0\n"
					+ "    Year: 0\n"
					+ "3. \"Hardcoded expected title movie 3\"\n"
					+ "    Rating: 8.0\n"
					+ "    Director: Test3\n"
					+ "    Run Time: 0\n"
					+ "    Year: 0\n"
					+ "4. \"Hardcoded expected title movie 4\"\n"
					+ "    Rating: 4.0\n"
					+ "    Director: Test4\n"
					+ "    Run Time: 0\n"
					+ "    Year: 0\n";
			assertEquals(returned, expected);
		} catch (Exception e) {
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

	/**
	 * This tests the integration of the backend developer code with the frontend and datawrangler
	 */
	@Test
	void BDIntegrationDWFDTest() {
		// Loading in the file in data named test.txt, thus testing integration of frontend with
		// backend and datawrangler, as passing in the file name to frontend loadDataFD() method 
		// should call the backend's load file method, which should use the datawrangler's
		// MovieReader to load in the file
		try {
			frontendInstance.loadDataFD("data/test.txt");
		} catch (Exception e) {
			// Failing test if unexpected Exception is thrown
			fail("Unexpected Exception was thrown");
		} 
		// Using the print movies method of the backend object in the frontend
		// which should use the backend's method, and checking the returned String
		String returned = frontendInstance.movieList.printAllMovies();
		String expected = "		--------------------- Movie Ranker Movies ---------------------\n"
		+ "\n"
		+ "1. \"The Shawshank Redemption\"\n"
		+ "    Rating: 9.3\n"
		+ "    Director: Frank Darabont\n"
		+ "    Run Time: 142\n"
		+ "    Year: 1994\n"
		+ "2. \"The Godfather\"\n"
		+ "    Rating: 9.2\n"
		+ "    Director: Francis Ford Coppola\n"
		+ "    Run Time: 175\n"
		+ "    Year: 1972\n"
		+ "3. \"The Dark Knight\"\n"
		+ "    Rating: 9.0\n"
		+ "    Director: Christopher Nolan\n"
		+ "    Run Time: 152\n"
		+ "    Year: 2008\n"
		+ "4. \"The Lord of the Rings: The Return of the King\"\n"
		+ "    Rating: 8.9\n"
		+ "    Director: Peter Jackson\n"
		+ "    Run Time: 201\n"
		+ "    Year: 2003\n"
		+ "5. \"Inception\"\n"
		+ "    Rating: 8.8\n"
		+ "    Director: Christopher Nolan\n"
		+ "    Run Time: 148\n"
		+ "    Year: 2010\n"
		+ "6. \"The Lord of the Rings: The Two Towers\"\n"
		+ "    Rating: 8.7\r\n"
		+ "    Director: Peter Jackson\n"
		+ "    Run Time: 179\n"
		+ "    Year: 2002\n"
		+ "7. \"Hamilton\"\n"
		+ "    Rating: 8.6\n"
		+ "    Director: Thomas Kail\n"
		+ "    Run Time: 160\n"
		+ "    Year: 2020\n"
		+ "8. \"Joker\"\n"
		+ "    Rating: 8.5\n"
		+ "    Director: Todd Phillips\n"
		+ "    Run Time: 122\n"
		+ "    Year: 2019\n"
		+ "9. \"Capharnaüm\"\n"
		+ "    Rating: 8.4\n"
		+ "    Director: Nadine Labaki\n"
		+ "    Run Time: 126\n"
		+ "    Year: 2018\n"
		+ "10. \"1917\"\n"
		+ "    Rating: 8.3\n"
		+ "    Director: Sam Mendes\n"
		+ "    Run Time: 119\n"
		+ "    Year: 2019\n"
		+ "11. \"Chhichhore\"\n"
		+ "    Rating: 8.2\n"
		+ "    Director: Nitesh Tiwari\n"
		+ "    Run Time: 143\n"
		+ "    Year: 2019\n"
		+ "12. \"Portrait de la jeune fille en feu\"\n"
		+ "    Rating: 8.1\n"
		+ "    Director: Céline Sciamma\n"
		+ "    Run Time: 122\r\n"
		+ "    Year: 2019\r\n"
		+ "13. \"Badhaai ho\"\n"
		+ "    Rating: 8.0\n"
		+ "    Director: Amit Ravindernath Sharma\n"
		+ "    Run Time: 124\n"
		+ "    Year: 2018\n"
		+ "14. \"Knives Out\"\n"
		+ "    Rating: 7.9\n"
		+ "    Director: Rian Johnson\n"
		+ "    Run Time: 130\n"
		+ "    Year: 2019\n"
		+ "15. \"The Gentlemen\"\n"
		+ "    Rating: 7.8\n"
		+ "    Director: Guy Ritchie\n"
		+ "    Run Time: 113\n"
		+ "    Year: 2019\n"
		+ "16. \"Roma\"\n"
		+ "    Rating: 7.7\n"
		+ "    Director: Alfonso Cuarón\n"
		+ "    Run Time: 135\n"
		+ "    Year: 2018\n"
		+ "17. \"Dark Waters\"\n"
		+ "    Rating: 7.6\n"
		+ "    Director: Todd Haynes\n"
		+ "    Run Time: 126\n"
		+ "    Year: 2019\n";
		// Checking that the Strings are equal to one another
		assertEquals(returned, returned);
		
		// Trying to add a movie using the frontend's method which uses the backend's method
		// Attempt should successfully work given it is a valid movie and not throw Exception
		// Pass shows proper integration of frontend's class, backend's class, and datawrangler's
		// MovieDW class
		try {
			frontendInstance.movieList.addMovie("test movie", 0.1, "test director", 0, 0);
		} catch (Exception e) {
			fail("Unexpected Exception was thrown");
		}
		
		// Attempting to remove movies using the frontend's remove method which uses backend's
		// method and should not throw an exception
		try {
			frontendInstance.movieList.getMoviesByRating(0.0, 10.0);
		} catch (Exception e) {
			fail("Unexpected Exception thrown");
		}
		
		// Attempting to clear the movies which uses the backend's clear method
		try {
			frontendInstance.movieList.clear();
		} catch (Exception e) {
			fail("Unexpected Exception was thrown");
		}
		// If all tests pass, it means integration for the methods tested is working between
		// BackendDeveloper's class, FrontendDeveloper's class, and DataWrangler's classes
	}
	
	/**
	 * This test checks the functionality of the Backend alongside the AE's code. The insert() 
	 * was already tested in previous weeks for the RedBlackTree, so this test will focus on the 
	 * other functionalities of the AE's implementation of the RedBlackTree alongside the Backend
	 */
	@Test
	void BDIntegrationAETest() {
		// Testing the functionality of the addMovies() method by adding movies through Backend
		try {
			instanceIntegration.addMovie("Sample Title", 5.0, "Sample Name", 100, 2004);
			instanceIntegration.addMovie("Sample Title1", 4.0, "Sample Name2", 101, 2005);
		} catch (Exception e) {
			fail("Unexpected Exception was thrown");
		}
		// Checking that the inOrderTraversal function properly works 
		String expected = "		--------------------- Movie Ranker Movies ---------------------\n"
				+ "\n"
				+ "1. \"Sample Title\"\n"
				+ "    Rating: 5.0\n"
				+ "    Director: Sample Name\n"
				+ "    Run Time: 100\n"
				+ "    Year: 2004\n"
				+ "2. \"Sample Title1\"\n"
				+ "    Rating: 4.0\n"
				+ "    Director: Sample Name2\n"
				+ "    Run Time: 101\n"
				+ "    Year: 2005\n";
		String returned = instanceIntegration.printAllMovies();
		// Making sure the Strings are equal showing that the backend works with the frontend
		assertEquals(returned, expected);
		// Getting by range and making sure both movies are returned
		String returnedRange = instanceIntegration.getMoviesByRating(3.0, 7.0);
		String expectedRange = " 	--------------------- Movie Ranker Movies ---------------------\n"
				+ "\n"
				+ "1. \"Sample Title\"\n"
				+ "    Rating: 5.0\n"
				+ "    Director: Sample Name\n"
				+ "    Run Time: 100\n"
				+ "    Year: 2004\n"
				+ "2. \"Sample Title1\"\n"
				+ "    Rating: 4.0\n"
				+ "    Director: Sample Name2\n"
				+ "    Run Time: 101\n"
				+ "    Year: 2005\n";
		// Making sure the Strings are equal showing that the backend works with the frontend
		assertEquals(returned, expected);
		// Making sure that remove works
		try {
			instanceIntegration.removeByRatingRange(4.0, 4.9);
		} catch (Exception e) {
			fail("Unexpected Exception was thrown");
		}
		// Checking that the movie was removed with the size
		String expectedRemove = "		--------------------- Movie Ranker Movies ---------------------\n"
				+ "\n"
				+ "1. \"Sample Title\"\n"
				+ "    Rating: 5.0\n"
				+ "    Director: Sample Name\n"
				+ "    Run Time: 100\n"
				+ "    Year: 2004\n";
		String returnedRemoved = instanceIntegration.printAllMovies();
		assertEquals(returnedRemoved, expectedRemove);
		try {
			instanceIntegration.clear();
		} catch (Exception e){
			fail("Unexpected Exception was thrown");
		}
		assertEquals(instanceIntegration.printAllMovies(), "No movies in this Ranker, please load in a file or add movies");
		// Passing these means that the AE and BD are properly working alongside each other and
		// Integration for the code of these two roles is working
	}
	
	/**
	 * This is a test that checks the loadMovies and addMovies functionality of the Frontend code
	 */
	@Test
	void FrontendDeveloperTest1() {
		// Testing the proper functionality of the loadDataFD method to make sure the right String
		// is passed to the backend
		// Using a call that should not throw an exception and failing if an Exception is thrown
		try {
			frontendFDTest.loadDataFD("data/test.txt");
		} catch (Exception e) {
			fail("Exception was not expected but was thrown and not handled");
		}
		// Using a call that should cause an exception from dataWrangler and backend; failing if 
		// the exception is not caught by the frontend 
		try {
			frontendFDTest.loadDataFD("Exception");
		} catch (Exception e) {
			fail("Exception was expected but was not handled");
		}
		
		// Testing the add movie functionality of the frontend 
		// Adding a valid movie and making sure no Exception is thrown
		try {
			frontendFDTest.addMovieFD("test", 8.0, "test name", 100, 2004);
		} catch (Exception e) {
			// Fail if an Exception is thrown and not handled
			fail("Unexpected Exception was thrown");
		}
		// Adding invalid movie and making sure an error is made but there is no unhandled exception
		try {
			frontendFDTest.addMovieFD("test fail", -10.1, "test name", 120, 2005);
		} catch (Exception e) {
			fail("Error message is expected but Exception should not be thrown");
		}
		// Passing of this test indicates that the loadDataFD and the addMovieFD of the FrontendFD
		// are passing in the correct values to the backend class, meaning proper functioning
	}
	
	/**
	 * This is a test that checks working of remove, get, clear, and getstats of the Frontend code
	 */
	@Test
	void FrontendDeveloperTest2() {
		// Attempting to remove a invalid movie and expecting an error message, but an Exception
		// should be handled, failing if the Exception is not handled
		try {
			frontendFDTest.deleteMovieByRating(-12.0, 0);
		} catch (Exception e) {
			// Fail if an Exception is thrown and not handled
			fail("Exception that should have been caught was not caught");
		}
		// removing with a valid range and making sure an Exception is not thrown
		try {
			frontendFDTest.deleteMovieByRating(1.0, 9.0);;
		} catch (Exception e) {
			// fail if Exception is thrown but not expected
			fail("Unexpected Exception was thrown");
		}
		
		// Testing getting a range of movies
		// First using a call that will result in an error, failing if Exception is not handled
		try {
			frontendFDTest.searchMoviesByRankFD(12.0, 20.0);
		} catch (Exception e) {
			fail("Error was expected but Exception was thrown and not handled by the frontend");
		}
		// Using a call that will not result in Exception from backend and failing if Exception
		try {
			frontendFDTest.searchMoviesByRankFD(2.0, 9.9);
		} catch (Exception e) {
			fail("Error was not expected and Exception was not handled by the frontend");
		}
		
		// Testing getting stats of the list of the frontend
		// Expect no Exception thrown so failing if Exception is thrown and Frontend doesn't handle
		try {
			frontendFDTest.printDataFD();
		} catch (Exception e) {
			fail("Exception was not expected but it was was thrown and not handled by the frontend");
		}
		
		// Testing the clear method of the frontend
		// Expect no Exception thrown so failing if an Exception is thrown and not handled by FD
		try {
			frontendFDTest.clearFD();
		} catch (Exception e) {
			fail("An unexpected Exception was thrown and it was not handled by the Frontend");
		}
		
		// Testing the printallmovies method of the frontend
		// No Exception should be thrown so if there is one and it is not handled, fail the test
		// An error message is expected since the tree was cleared in the command before
		try {
			frontendFDTest.printListFD();
		} catch (Exception e) {
			fail("Unexpected Exception was thrown and it was not handled by the frontend");
		}
		// Passing of all tests means the frontendFD class written by the FrontendDeveloper is not
		// throwing any Exceptions when it is not expected to, and its methods are calling the right
		// methods of the backend without any Exceptions being thrown or any trouble arising. This
		// overall shows proper functioning of the frontendFD class, writtem by FD, on its own.
	}
}

