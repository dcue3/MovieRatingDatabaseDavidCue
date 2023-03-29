import java.io.FileNotFoundException;
import java.util.List;

public interface MovieRankingInterface {
  
	// Fields for the class
	//private MovieReaderInterface<MovieInterface> MovieReaderDW; 
	//private RedBlackTreeInterface<Value> RedBlackTreeAE;   
	
	// Constructors
	    // public MovieRankingInterface(MovieReaderInterface MovieReader, RedBlackTreeInterface<MovieInterface> RBT)         
	
	// Methods
	public void loadMoviesList(String filename) throws FileNotFoundException;  
    	public String getMoviesByRating(double x, double y);
    	public void addMovie(String name, double rating, String directorName, int runtime, int year);
   	public void removeByRatingRange(double x, double y);
    	public String printAllMovies();
    	public void clear();
    	public String getData();

}
