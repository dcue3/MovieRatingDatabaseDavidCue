import java.io.FileNotFoundException;
import java.util.List;

public interface MovieRankingInterface {
  
	// Fields for the class
	//private MovieReaderInterface<MovieInterface> MovieReaderDW; 
	//private RedBlackTreeInterface<Value> RedBlackTreeAE;   
	
	// Constructors
		// public MovieRankingInterface()         
		// public MovieRankingInterface(int maxSize)
	
	// Methods
        public void loadMoviesList(String filename) throws FileNotFoundException;  
        public List<MovieInterface> getMoviesByRating(double x, double y);
        public void addMovie(String name, double rating, String directorName, int runtime, int year);
        public void removeByRating(double rating);
        public List<MovieInterface> printAllMovies();
        public void clear();

}
