import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class FrontendDeveloperTests {
  
  
 
  @Test
  public void addTest() {//testing the frontend method to add a movie
    
    try {
    FrontendFD frontend = new FrontendFD(new MovieRankingFD()); //creating frontend 
    
    
    frontend.addMovieFD("New Movie", 9.9, "David Cue", 120, 2012); //adding movie
    
    //search for this movie and compare it to the expected output, if it is not there, fail test. 
    if(frontend.movieList.getMoviesByRating(9.9, 9.9).compareTo("New Movie, 120, 2012 min, rated 9.9, by David Cue")!=0) {Assertions.fail();}
    }catch (Exception e) {Assertions.fail();} //fail test if there are are unexpected exceptions
    
  }
  
  @Test
  public void removeTest() {//testing the frontend method to add a movie
    
    FrontendFD frontend = new FrontendFD(new MovieRankingFD());//creating frontend
    

    
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
  
    FrontendFD frontend = new FrontendFD(new MovieRankingFD());//creating frontend
  
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
  
    FrontendFD frontend = new FrontendFD(new MovieRankingFD()); //creating frontend
  
  frontend.loadDataFD("testname"); //entering "testname" to method is hardcoded to load a singular movie. 
  
  
  if(frontend.movieList.getMoviesByRating(7.8, 7.8).compareTo("SampleName, 23, 2012 min, rated 7.8, by david cue")!=0) {Assertions.fail();}

  frontend.loadDataFD("h"); //trying to load nonexistent file
  
  if(frontend.movieList.getMoviesByRating(7.8, 7.8).compareTo("SampleName, 23, 2012 min, rated 7.8, by david cue")!=0) {Assertions.fail();}
  //list should be the same as before

}

  @Test
  public void searchTest() {//testing the frontend method to add a movie
  
    FrontendFD frontend = new FrontendFD(new MovieRankingFD()); //creating frontend
  
  
  frontend.addMovieFD("New Movie", 9.9, "David Cue", 120, 2012); //adding movie
  
  //testing searching for the movie's rating, string output should be equal to the movies string. 
  if(frontend.movieList.getMoviesByRating(9.9, 9.9).compareTo("New Movie, 120, 2012 min, rated 9.9, by David Cue")!=0) {Assertions.fail();}
  //testing the same as above but with a wider range of search values
  if(frontend.movieList.getMoviesByRating(9.0, 10).compareTo("New Movie, 120, 2012 min, rated 9.9, by David Cue")!=0) {Assertions.fail();}
  try { //trying to search for nonexistent movie, should return empty string without throwing exception. 
  if(frontend.movieList.getMoviesByRating(1, 2).length()!=0) {Assertions.fail();}
  }catch(Exception e ) {Assertions.fail();}

}


@Test
  public void CodeReviewOfDataWrangler() {
   
    try {  //testing reading from a custom file with the following contents: (titled FrontendDWTest.txt)
      //David Cue Test,2023,120 min,8.6,David Cue
      //David Cue Test 2,2023,121 min,9.0,Quentin Tarantino
      List<MovieInterface> mvList =null;
      MovieReaderInterface mv = new MovieReaderDW();
      mvList = mv.readMoviesFromFile("./FrontendDWTest.txt");
      if(!mvList.get(0).getTitle().equals("David Cue Test")) {
        Assertions.fail();
      } //testing that movies were loaded correctly
      if(mvList.get(1).getReleasedYear()!=2023) {
        Assertions.fail();
      }
      if(mvList.get(0).getRuntime()!=120) {
        Assertions.fail();
      }
      
      
    }catch(Exception e) {Assertions.fail();}
    
  }
  
  @Test
  public void CodeReviewOfBackendDeveloper() {
    try {
      RedBlackTreeAE<MovieInterface> rbt = new RedBlackTreeAE<MovieInterface>(); 
      MovieReaderDW mvReader = new MovieReaderDW();
      MovieRanking ranking = new MovieRanking(mvReader, rbt);
      //creating movieranking with RBT and movie reader
      ranking.addMovie("David Cue", 10.0, "hello", 0, 0);
      ranking.addMovie("David Cue 2", 9.0, "hello 2", 0, 0);
      //adding movies through backend
      String testString = ranking.printAllMovies();
      //testing through backend methods that movies were added correctly
      String expectedReturn = "             --------------------- Movie Ranker Movies ---------------------\n"
          + "\n"
          + "1. \"David Cue\"\n"
          + "    Rating: 10.0\n"
          + "    Director: hello\n"
          + "    Run Time: 0\n"
          + "    Year: 0\n"
          + "2. \"David Cue 2\"\n" + "    Rating: 9.0\n"
          + "    Director: hello 2\n"
          + "    Run Time: 0\n"
          + "    Year: 0\n";

      
      assertEquals(testString.replaceAll("\\s+", ""),expectedReturn.replaceAll("\\s+", ""));
      
      
      
    }catch(Exception e) {Assertions.fail();}
    
    
  }

@Test
  public void IntegrationTestDW() { 
    try {
    //tests both backend and DW integration with frontend
    RedBlackTreeAE<MovieInterface> RBTInstance = new RedBlackTreeAE<MovieInterface>();
    MovieReaderDW MovieReaderInstance = new MovieReaderDW();
    MovieRanking testBD = new MovieRanking(MovieReaderInstance, RBTInstance);
    FrontendFD testFD = new FrontendFD(testBD); //creating working program
    
    
    testFD.loadDataFD("./FrontendDWTest.txt"); //passing file name from frontend
    //will go through backend method then to datawrangler. 
    
    //using backend to get string representation of tree to check if it was loaded successfully 
  String check =testFD.movieList.printAllMovies();
  
String expected =  "             --------------------- Movie Ranker Movies ---------------------\n"
      + "\n"
      
      + "1. \"David Cue Test 2\"\n" + "    Rating: 9.0\n"
      + "    Director: Quentin Tarantino\n"
      + "    Run Time: 121\n"
      + "    Year: 2023\n"
      + "2. \"David Cue Test\"\n"
      + "    Rating: 8.6\n"
      + "    Director: David Cue\n"
      + "    Run Time: 120\n"
      + "    Year: 2023\n";
  assertEquals(check.replaceAll("\\s+", ""),expected.replaceAll("\\s+", ""));

    }catch(Exception e) {Assertions.fail();}
  }

@Test
  public void IntegrationTestAE() {
  //integration test primarily for AE  
    RedBlackTreeAE<MovieInterface> RBTInstance = new RedBlackTreeAE<MovieInterface>();
    MovieReaderDW MovieReaderInstance = new MovieReaderDW();
    MovieRanking testBD = new MovieRanking(MovieReaderInstance, RBTInstance);
    FrontendFD testFD = new FrontendFD(testBD); //creating working program
    
    testFD.addMovieFD("David", 7.8, "D C", 0, 0);
    testFD.addMovieFD("David 2", 7.9, "D C", 0, 0);
    testFD.addMovieFD("David 3", 8.0, "D C", 0, 0);
    testFD.deleteMovieByRating(7.8, 7.8);
    testFD.addMovieFD("David 4", 8.1, "D C", 0, 0);
    testFD.addMovieFD("David 5", 8.2, "D C", 0, 0);
    //adding movies
    String testString = testFD.movieList.getData();
    //checking that all were added correctly and the statistics are correctly calculated
    String expected =  "--------------------- Movie Ranker Statistics "
        + "---------------------\n\n"
        + "There are currently 4 movie(s) in this MovieRanking\n\n"
        + "The average rating across all movies is: 8.05\n\n"
        + "The highestrated movie is: \"David 5\"\n"
        + "    Rating: 8.2\n"
        + "    Director: D C\n"
        + "    Run Time: 0\n"
        + "    Year: 0\n\n"
        + "The lowest rated movie is: \"David 2\"\n"
        + "    Rating: 7.9\n"
        + "    Director: D C\n"
        + "    Run Time: 0\n"
        + "    Year: 0";
assertEquals(testString.replaceAll("\\s+", ""), expected.replaceAll("\\s+", ""));
    
    
    
  }
  
}
