import java.io.FileNotFoundException;
import java.util.List;

public interface MovieRankingInterface {   
//    private MovieReaderInterface<MovieInterface> MovieReaderDW; 
//    private RedBlackTreeInterface<Value> RedBlackTreeAE,     
    // public MovieRankingInterface()             
    // public MovieRankingInterface(int maxSize)                                                                    
    public void loadMoviesList(String filename) throws FileNotFoundException;
    public String getMoviesByRating(double x, double y);
    public void addMovie(String name, double rating, String directorName, int runtime, int year);
    public void removeByRatingRange(double x, double y);
    public String printAllMovies();
    public String getData();
    public void clear();
}
