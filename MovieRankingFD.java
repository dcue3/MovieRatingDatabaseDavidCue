import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MovieRankingFD implements MovieRankingInterface{

  List<MovieInterface> list = new ArrayList<MovieInterface>();
  @Override
  public void loadMoviesList(String filename) throws FileNotFoundException {
    // TODO Auto-generated method stub
    if(filename.equals("testname")) {
      list.add(new MovieFD("SampleName",23,2012,7.8,"david cue"));
    }else {throw new FileNotFoundException();}
  }

  @Override
  public String getMoviesByRating(double x, double y) {
    
    
    String d ="";
    
    
    if(list.size()!=0) {
    for(int i=0;i<list.size();i++) {
      if(list.get(i).getRating()>=x && list.get(i).getRating()<=y) {
        d = d+ list.get(i).getTitle() + ", " +  list.get(i).getReleasedYear() + ", " + list.get(i).getRuntime() + " min, rated " + list.get(i).getRating() + ", by " + list.get(i).getDirectorName();
      }
    }
    }
    
    
    return d;
  }

  @Override
  public void addMovie(String name, double rating, String directorName, int runtime, int year) {
    // TODO Auto-generated method stub
    list.add(new MovieFD(name,runtime,year,rating,directorName));
    
  }

  @Override
  public void removeByRatingRange(double x, double y) {
    // TODO Auto-generated method stub
    boolean removed=false;
    if(list.size()!=0) {
    for(int i=0;i<list.size();i++) {
      if(list.get(i).getRating()>=x && list.get(i).getRating()<=y) {
        list.remove(i);
        removed=true;
      }
    }}
    if(removed==false) {throw new NoSuchElementException();}
  }

  @Override
  public String printAllMovies() {
    String d = "";
    
    for(int i=0; i<list.size();i++) {
      d=d+ list.get(i).getTitle() + ", " +  list.get(i).getReleasedYear() + ", " + list.get(i).getRuntime() + " min, rated " + list.get(i).getRating() + ", by " + list.get(i).getDirectorName();
    }
   
    return d;
  }

  @Override
  public String getData() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub
    list.clear();
  }
  

  
  
}