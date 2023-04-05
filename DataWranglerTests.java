// --== CS400 Spring 2023 File Header Information ==--
// Name: Asish Das
// Email: das38@wisc.edu
// Team: RED(DF)
// TA: Callie
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;


public class DataWranglerTests {
	
        /**
         * Junit test to test the readMoviesFromFile of the MoviesReaderDW class.
         * Also test for getTitle() and getRating() method in the MovieDW class
         */
        @Test
        public void test1() {
                List<MovieInterface> movies = null;
                MovieReaderInterface movie_reader = new MovieReaderDW();
                try {
                        movies = movie_reader.readMoviesFromFile("./data/test.txt");
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                // checking if the readMoviesFromFile read data from the entire test.txt file
                assertEquals(movies.size(), 1000); // there are 1000 movies in the dataset
                // the first movie in the dataset is The Shawshank Redemption"
                assertEquals(movies.get(0).getTitle(), "The Shawshank Redemption");
                //checking the rating of the first movie
                assertEquals(movies.get(0).getRating(), Double.valueOf(9.3));
        }

        /**
         * Junit test for checking the correctness of the getting runtime for each movies
         * since the runtime was in the format of (time min)
         * Also testing the .getRuntime() method of MovieDW class.
         */
        @Test
	public void test2() {
                List<MovieInterface> movies = null;
                MovieReaderInterface movie_reader = new MovieReaderDW();

                try {
                        movies = movie_reader.readMoviesFromFile("./data/test.txt");
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                assertEquals(movies.get(1).getRuntime(), 175);
                assertEquals(movies.get(2).getRuntime(), 152);
                assertEquals(movies.get(50).getRuntime(), 102);
        }

        /**
         * JUnit test for testing the first compareTo methods of MovieDW class
         */
        @Test
        public void test3() {
                //comparing just with double value of rating
                List<MovieInterface> movies = null;
                MovieReaderInterface movie_reader = new MovieReaderDW();

                try {
                        movies = movie_reader.readMoviesFromFile("./data/test.txt");
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                // because we know the rating of the first movie is greater than 5
                assertEquals(movies.get(0).compareTo(5.0), 1);
        }

        /**
         * JUnit test for testing the second compare method
         */
        @Test
        public void test4() {
                List<MovieInterface> movies = null;
                MovieReaderInterface movie_reader = new MovieReaderDW();

                try {
                        movies = movie_reader.readMoviesFromFile("./data/test.txt");
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }

                // comparing the objects based on ratings.
                // creating a movie object
                MovieInterface testMovie = new MovieDW("some title", 2023, 120, 8.5, "Christopher Nolan");
                //comparing this with the first movie should return -1
                assertEquals(testMovie.compareTo(movies.get(0)), -1); // because its rating is lower.
                //comparing with Terminator 2: Judgment Day should return 0 because its rating is also 8.5
                assertEquals(testMovie.compareTo(movies.get(44)), 0);
        }

        /**
         * Junit tests for get released year and readMoviesFromFile
         */
        @Test
        public void test5() {
                List<MovieInterface> movies = null;
                MovieReaderInterface movie_reader = new MovieReaderDW();

                try {
                        movies = movie_reader.readMoviesFromFile("./data/test.txt");
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                //checking individual released year.
                assertEquals(movies.get(1).getReleasedYear(), 1972);
                assertEquals(movies.get(3).getReleasedYear(), 1974);
        }
	
	/**
	 *Integration Test 1
	 * combining DataWrangler(mine), AE, and backend to test some methods
	 */
	@Test
	public void IntegrationTest1(){
		RedBlackTreeInterface RBTInstance = new RedBlackTreeAE<MovieInterface>();
                MovieReaderInterface movie_reader = new MovieReaderDW();
                MovieRankingInterface movierankerBD = new MovieRanking(movie_reader, RBTInstance);
                try{
                        movierankerBD.loadMoviesList("./data/test.txt");
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
		//movierankerBD.removeByRatingRange(8.0, 10.0);
		//System.out.println(movierankerBD.getMoviesByRating(6.0, 8.0));		
		assertThat(movierankerBD.getMoviesByRating(6.0, 8.0), containsString("Title: Badhaai ho")); //the first movie with the 8.0 rating	
	}
	/**
	 *Integration Test 2
	 *combining DataWrangler(mine), AE, and backend to test some methods
	 */
	@Test
	public void IntegrationTest2(){
		RedBlackTreeInterface RBTInstance = new RedBlackTreeAE<MovieInterface>();
                MovieReaderInterface movie_reader = new MovieReaderDW();
                MovieRankingInterface movierankerBD = new MovieRanking(movie_reader, RBTInstance);
                try{
                        movierankerBD.loadMoviesList("./data/test.txt");
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
		movierankerBD.removeByRatingRange(6.0, 8.0);
		//after removing it should only contain 12 movies
		assertThat(movierankerBD.getData(), containsString("12"));
	}

	/**
	 *Junit test for algorithm engineer methods
	 */
	@Test
	public void CodeReviewOfAlgorithmEngineer(){
		//testing the enforce method
		RedBlackTreeAE<Integer> tree = new RedBlackTreeAE<Integer>();
    		tree.insert(10);
    		tree.insert(5);
    		tree.insert(12);
    		tree.insert(4);
    		assertEquals(tree.root.data, Integer.valueOf(10));
    		assertEquals(tree.root.blackHeight, 1); // root is always black
    		assertEquals(tree.root.context[1].blackHeight, 1); // 5 should be black
    		assertEquals(tree.root.context[2].blackHeight, 1); //12 should be black
    		assertEquals(tree.root.context[1].context[1].blackHeight, 0); // 4 should be red
		// testing the getbyrange method
		List<Integer> test = tree.getByRange(10, 12);
		List<Integer> expected = new ArrayList<>();
		expected.add(10);
		expected.add(12);
		assertEquals(expected, test);
	}

	/**
	 *Junit test for codereview of Backend methods
	 *testing the loadData method and getData() method.
	 */
	@Test
	public void CodeReviewofBackendDeveloper(){
	       RedBlackTreeInterface RBTInstance = new RedBlackTreeAE<MovieInterface>();	
		MovieReaderInterface movie_reader = new MovieReaderDW();
		MovieRankingInterface movierankerBD = new MovieRanking(movie_reader, RBTInstance);
		try{
			movierankerBD.loadMoviesList("./data/test.txt");
		} catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
		//System.out.println(movierankerBD.getData());
		assertThat(movierankerBD.getData(), containsString("Rating: 9.3")); //it contains the highest rated movie of rating 9.3

	}	
}
