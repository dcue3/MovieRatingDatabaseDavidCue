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
	 * JUnit test for testing the two compareTo methods of MovieDW class
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
		// comparing the objects based on ratings.
		// creating a movie object
		MovieInterface testMovie = new MovieDW("some title", 2023, 120, 8.5, "Christopher Nolan");
		//comparing this with the first movie should return -1
		assertEquals(testMovie.compareTo(movies.get(0)), -1); // because its rating is lower.
		//comparing with Terminator 2: Judgment Day should return 0 because its rating is also 8.5
		assertEquals(testMovie.compareTo(movies.get(44)), 0);
	}
}
