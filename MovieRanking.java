// --== CS400 Spring 2023 File Header Information ==--
// Name: Sudheesh Dabbara
// Email: sdabbara@wisc.edu
// Team: DF - Red
// TA: Callie Kim
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class models a MovieRanking which implements MovieRankingInterface and makes use of an RBT to store and rank Movie objects based on rating
 *
 * @author Sudheesh Dabbara
 *
**/
public class MovieRanking implements MovieRankingInterface {
	
	// Objects required to make a MovieRanking
	private MovieReaderInterface MovieReaderDW; 
	private RedBlackTreeInterface<MovieInterface> RedBlackTreeAE;
	int size;
	
	// Constructor
	public MovieRanking(MovieReaderInterface MovieReader, RedBlackTreeInterface<MovieInterface> RBT) {
		this.size = 0;
		this.MovieReaderDW = MovieReader;
		this.RedBlackTreeAE = RBT;
	}
	
	/**
	 * This method uses the DW's loadMovies method to load movies into this RBT
	 * @param filename is the name of the file to be loaded in
	 * @throws FileNotFoundException if the file is not found 
	**/
	@Override
	public void loadMoviesList(String filename) throws FileNotFoundException {
		// Using DW's method to get a list of movies
		List<MovieInterface> moviesToAdd = new ArrayList<MovieInterface>();
		moviesToAdd = (List<MovieInterface>) MovieReaderDW.readMoviesFromFile(filename);
		if (moviesToAdd.size() == 0) {
			return;
		}
		// Adding each movie to the RBT using the insert() method of RBT
		for (int i = 0; i < moviesToAdd.size(); i++) {
		    try {
			RedBlackTreeAE.insert(moviesToAdd.get(i));
			this.size++;
		    } catch (IllegalArgumentException e) {
		    }		
		}
	}
	
	/**
         * This method uses the AE's methods to get the movies within a specific range of rating 
         * @param x is the lower bound of rating
	 * @param y us the upper bound of rating
         * @return a formatted String of all movies in the range provided  
        **/
	@Override
	public String getMoviesByRating(double x, double y) {
		// Creating two new movies using the ranges to pass to the AE method
		MovieInterface movieX = new MovieDW(null, 0, 0, x, null);
		MovieInterface movieY = new MovieDW(null, 0, 0, y, null);
		// Getting the List of movies using the AE's method
		String toReturn = null;
		List<MovieInterface> movies = (List<MovieInterface>) RedBlackTreeAE.getByRange(movieX, movieY);
		// Returning a message if the List is empty
		if (movies.size() == 0) {
			return "No movies added between ranges " + x + " and " + y + ", please load in a file"
					+ " or add movies";
		}
		// Adding to the String toReturn each Movie formatted properly
		for (int i = movies.size() - 1; i >= 0 ; i--) {
			toReturn += ((movies.size() - i) + 1) + ". Title: "  + movies.get(i).getTitle() + ", "
					+ "Rating: " 
					+ movies.get(i).getRating() + ", Director: " + movies.get(i).getDirectorName()
					+ ", Run Time: " + movies.get(i).getRuntime()
					+ ", Year: " + movies.get(i).getReleasedYear() + "\n" ;
		}
		// Returning the String
		return toReturn;
	}
	
	/**
         * This method uses the AE's methods to add a movie to the RBT given information about the movie 
         * @param name is the name of the movie
	 * @param rating is the rating of the movie
	 * @param directorName is the name of the director for the movie
	 * @runtime is the runtime of the movie
         * @param year is the year the movie was produced in   
        **/
	@Override
	public void addMovie(String name, double rating, String directorName, int runtime, int year) {
		// Creating the movie and adding it using the information provided
		MovieInterface toAdd = new MovieBD(name, year, runtime, rating, directorName);	
		try {
			RedBlackTreeAE.insert(toAdd);
			this.size++;
		} catch (IllegalArgumentException e) {
		}
	}
	
	/**
         * This method uses the AE's methods to remove the movies within a specific range of rating 
         * @param x is the lower bound of rating
         * @param y us the upper bound of rating   
        **/
	@Override
	public void removeByRatingRange(double x, double y) {
		MovieInterface movieX = new MovieDW(null, 0, 0, x, null);
                MovieInterface movieY = new MovieDW(null, 0, 0, y, null);
		// Getting the list of movies in the provided range 
		List<MovieInterface> movies = RedBlackTreeAE.getByRange(movieX, movieY);
		if (movies.size() == 0) {
			System.out.println("No movies with ratings in range of " + x + " and " + y);
			return;
		}
		// For each movie in the list, use remove() from the AE to remove each movie
		for (int i = 0; i < movies.size(); i++) {		
			RedBlackTreeAE.remove(movies.get(i));
			this.size--;
		}
	}
	
	/**
         * This method uses the AE's methods to get all Movies in the RBT and return them in order based on ranking
         * @return a formatted String of all movies in this MovieRanking 
        **/
	@Override
	public String printAllMovies() {
		// Creating a String toReturn and get all movies in the RBT with inOrderTraversal() method of the AE
		String toReturn = "		--------------------- Movie Ranker Movies ---------------------"
				+ "\n\n";
		List<MovieInterface> movies = RedBlackTreeAE.inOrderTraversal();
		// If the List is empty, return the appropriate message
		if (movies.size() == 0) {
			return "No movies in this Ranker, please load in a file or add movies";
		}
		// For each movie in the List, add it to the String toReturn properly formatted
		for (int i = movies.size() - 1; i >= 0 ; i--) {
			toReturn += ((movies.size() - i)) + ". \""  + movies.get(i).getTitle() + "\", Rating: " 
					+ movies.get(i).getRating() + ", Director: " + movies.get(i).getDirectorName()
					+ ", Run Time: " + movies.get(i).getRuntime()
					+ ", Year: " + movies.get(i).getReleasedYear() + "\n" ;
		}
		// Return the properly formatted String with all movies in the MovieRanking ranked
		return toReturn;
	}
	
	/**
         * This method uses the AE's methods to get the List of all movies and get the data of this MovieRanking 
         * @return a formatted String of the number of movies, avg rating, highest and lowest rated movies, in this MovieRanking
        **/
	@Override
	public String getData() {
		// Returning the appropriate message if the MovieRanking is empty
		String toReturn = "";
		if (this.size == 0) {
			toReturn = "The current Movie Ranking is empty, please add movies or load in a file of "
					+ "movies to see statistics.";
			return toReturn;
		}
		// Otherwise, getting the List of movies in the RBT, and finding min and max in the RBT
		List<MovieInterface> moviesList = RedBlackTreeAE.inOrderTraversal();
		double avgRating = 0;
		MovieInterface highestRated = moviesList.get(moviesList.size()-1);
		MovieInterface lowestRated = moviesList.get(0);
		// For each movie in the list, getting its rating and adding it to total rating to calculate average later
		for (int i = 0; i < moviesList.size(); i++) {
			avgRating = (avgRating + moviesList.get(i).getRating());
		}
		// Making a properly formatted String using the data collected above about this MovieRanking
		toReturn ="--------------------- Movie Ranker Statistics ---------------------\n\n"
				+ "There are currently " + moviesList.size() + " movie(s) in this MovieRanking"
				+ "\n\nThe average rating across all movies is: " + avgRating/moviesList.size()
				+ "\n\nThe "
				+ "highest"	+ "rated movie is: \"" + highestRated.getTitle() + "\"\n    Rating: " 
				+ highestRated.getRating() + "\n    Director: " + highestRated.getDirectorName()
				+ "\n    Run Time: " + highestRated.getRuntime()
				+ "\n    Year: " + highestRated.getReleasedYear() + "\n\nThe lowest rated movie is:"
				+ " \"" + lowestRated.getTitle() + "\"\n    Rating: " + lowestRated.getRating() 
				+ "\n    Director: " + lowestRated.getDirectorName()
				+ "\n    Run Time: " + lowestRated.getRuntime()
				+ "\n    Year: " + lowestRated.getReleasedYear();
		// Return the formatting String containing information about the statistics of this MovieRanking
		return toReturn;
	}
	
	/**
	 * This method clears the current MoveRanking using the clear() method of the AE's RBT
	**/
	@Override
	public void clear() {
		// Using the AE's method in RedBlackTree to clear the RBT
		RedBlackTreeAE.clear();
	}
	
}
