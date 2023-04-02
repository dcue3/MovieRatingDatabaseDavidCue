public interface FrontendInterface{
//Private MovieRankingInterface movieList;
public void runCommandLoop();
public String menu();
public void loadDataFD(String fileName);
public void deleteMovieByRating(double x, double y);
public void addMovieFD(String name, double rating, String directorName, int runtime, int year);
public void searchMoviesByRankFD(double y, double x);
public void printListFD();
public void clearFD();
}

