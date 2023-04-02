
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class FrontendDeveloperTests {
  
  
 
  @Test
  public void addTest() {//testing the frontend method to add a movie
    
    try {
    FrontendFD frontend = new FrontendFD(); //creating frontend 
    
    
    frontend.addMovieFD("New Movie", 9.9, "David Cue", 120, 2012); //adding movie
    
    //search for this movie and compare it to the expected output, if it is not there, fail test. 
    if(frontend.movieList.getMoviesByRating(9.9, 9.9).compareTo("New Movie, 120, 2012 min, rated 9.9, by David Cue")!=0) {Assertions.fail();}
    }catch (Exception e) {Assertions.fail();} //fail test if there are are unexpected exceptions
    
  }
  
  @Test
  public void removeTest() {//testing the frontend method to add a movie
    
    FrontendFD frontend = new FrontendFD();//creating frontend
    

    
    frontend.addMovieFD("New Movie", 9.9, "David Cue", 120, 2012); //adding movie
    
    frontend.deleteMovieByRating(0,0); //deleting ratings with 0, movie should still be in list
    
    //checking if condition above is true
    if(frontend.movieList.getMoviesByRating(9.9, 9.9).compareTo("New Movie, 120, 2012 min, rated 9.9, by David Cue")!=0) {Assertions.fail();}

    //now try deleting the movie added
    frontend.deleteMovieByRating(9.9,9.9);
    
    //if movie is not deleted, test fails
    if(frontend.movieList.getMoviesByRating(9.9, 9.9).length()!=0) {Assertions.fail();}
    

  }
  
  @Test
 public void clearTest() {//testing the frontend method to add a movie
  
  FrontendFD frontend = new FrontendFD(); //creating frontend
  
  //adding two different movies
  frontend.addMovieFD("New Movie", 9.9, "David Cue", 120, 2012);
  frontend.addMovieFD("New Movie 2", 9.1, "David Cue", 120, 2012);
  
  //clearing
  frontend.clearFD();
  
  //if there are any movies left, fail
  if(frontend.movieList.getMoviesByRating(9.9, 9.9).length()!=0) {Assertions.fail();}
  

}

  @Test
  public void loadTest() {//testing the frontend method to add a movie
  
  FrontendFD frontend = new FrontendFD(); //creating frontend
  
  frontend.loadDataFD("testname"); //entering "testname" to method is hardcoded to load a singular movie. 
  
  
  if(frontend.movieList.getMoviesByRating(7.8, 7.8).compareTo("SampleName, 23, 2012 min, rated 7.8, by david cue")!=0) {Assertions.fail();}

  frontend.loadDataFD("h"); //trying to load nonexistent file
  
  if(frontend.movieList.getMoviesByRating(7.8, 7.8).compareTo("SampleName, 23, 2012 min, rated 7.8, by david cue")!=0) {Assertions.fail();}
  //list should be the same as before

}

  @Test
  public void searchTest() {//testing the frontend method to add a movie
  
  FrontendFD frontend = new FrontendFD(); //creating frontend
  
  
  frontend.addMovieFD("New Movie", 9.9, "David Cue", 120, 2012); //adding movie
  
  //testing searching for the movie's rating, string output should be equal to the movies string. 
  if(frontend.movieList.getMoviesByRating(9.9, 9.9).compareTo("New Movie, 120, 2012 min, rated 9.9, by David Cue")!=0) {Assertions.fail();}
  //testing the same as above but with a wider range of search values
  if(frontend.movieList.getMoviesByRating(9.0, 10).compareTo("New Movie, 120, 2012 min, rated 9.9, by David Cue")!=0) {Assertions.fail();}
  try { //trying to search for nonexistent movie, should return empty string without throwing exception. 
  if(frontend.movieList.getMoviesByRating(1, 2).length()!=0) {Assertions.fail();}
  }catch(Exception e ) {Assertions.fail();}

}

 
  
}
