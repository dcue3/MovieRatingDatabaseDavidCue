import java.io.FileNotFoundException;
import java.util.List;

public interface MovieRankingInterface {
  
	// Fields for the class
	private MovieReaderInterface<MovieInterface> MovieReaderDW; 
	private RedBlackTreeInterface<Value> RedBlackTreeAE;   
	
	// Constructors
		// public MovieRankingInterface()         
		// public MovieRankingInterface(int maxSize)
	
	// Methods
	public void loadMoviesList(String filename) throws FileNotFoundException;	
	public double getRating(String name);
	public int getRank(String name);
	public void addMovie(String name);
	public void removeMovie(String name);
	public int findYear(String name);
	public List<MovieInterface> printAllMovie();
	public void clear();

}
