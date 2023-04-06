
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;



public class FrontendFD implements FrontendInterface{
  
public MovieRankingInterface movieList;

public FrontendFD(MovieRankingInterface mov) {
  movieList=mov;
}

public void runCommandLoop() {
  Scanner h;
  
  int choice1 =-1;
  
  
  while(choice1!=0) {
    h= new Scanner(System.in);
    int choice=-1;
    System.out.println(menu());
    try {
    choice=h.nextInt();
    h.nextLine();
    if(choice<0 || choice>7) {throw new Exception();}
    }catch(Exception e) {System.out.println("Please enter valid choice");}
    
    if(choice==1) { 
      System.out.println("Enter name of file: ");
      String name = h.nextLine();
      loadDataFD(name);
   
    }
    else if(choice==2) {
      String name;
      double rating;
      String dir;
      int time;int year;
      
      try {
        
      System.out.println("Enter name of movie: ");
      name = h.nextLine();
      System.out.println("Enter rating of movie (0-10): ");
       rating = h.nextDouble();
       h.nextLine();
      System.out.println("Enter director of movie: ");
      dir = h.nextLine();
      System.out.println("Enter runtime of movie(minutes): ");
       time = h.nextInt();
      System.out.println("Enter year of movie: ");
       year = h.nextInt(); 
       addMovieFD(name,rating,dir,time,year);
       
      }catch(Exception e) {System.out.println("Please input correctly formatted information");}
      
    }
    else  if(choice==3) {try {
      System.out.println("Enter range of ratings (enter a double for lower bound then for upper bound, both between 0-10): ");
      System.out.println("Lower bound: ");
      double y = h.nextDouble();
      System.out.println("Upper bound: ");
      double x = h.nextDouble();
      if(y>x || y<0 || x>10) {System.out.println("Error: Lower bound is greater than upper bound");
      throw new Exception();}
      deleteMovieByRating(y,x);
    }catch(Exception e) {System.out.println("Please input correctly formatted information");}
    }
    else if(choice==4) {
      try {
      System.out.println("Enter range of ratings (enter a double for lower bound then for upper bound, both between 0-10): ");
      System.out.println("Lower bound: ");
      double y = h.nextDouble();
      System.out.println("Upper bound: ");
      double x = h.nextDouble();
      if(y>x || y<0 || x>10) {System.out.println("Error: Lower bound is greater than upper bound");
      throw new Exception();}
      searchMoviesByRankFD(y, x);
      }catch(Exception e) {System.out.println("Please input correctly formatted information");}

    }
    else if(choice==5) {
      printListFD();
    }
    else if(choice==6) {
      clearFD();
    }
    else if(choice==7){
    printDataFD();
    }
    else if(choice==0) {
      choice1=0;
    }
    
    
  }
  
  
  
}

public String menu() {
  String menu = "Welcome to our movie rating database, please select an option: \n" ;
  menu += "1. Load movie data \n";
  menu += "2. Add movie to database \n";
  menu += "3. Delete movie from database \n";
  menu += "4. Search for movies by rating \n";
  menu += "5. Print movie list \n";
  menu += "6. Clear movie list \n";
  menu += "7. Display movie list stats \n";
  menu += "0. Quit \n";
  
  
  return menu;
}

public void loadDataFD(String fileName) {
  
  
  try {
    movieList.loadMoviesList(fileName);
    System.out.println("Loaded movie(s) succesfully");
  } catch (Exception e) {
    System.out.println("Error: invalid file name");
  }
  
  
}

public void deleteMovieByRating(double x, double y) {
  try {
    movieList.removeByRatingRange(x,y);
    System.out.println("Deleted movie(s) succesfully");
  } catch (Exception e) {
    System.out.println("Error: movie may not have been deleted");
  }
  
  
}

public void addMovieFD(String name, double rating, String directorName, int runtime, int year) {
  try { if(rating <0 || rating>10) {System.out.println("Invalid rating"); throw new Exception();}
    movieList.addMovie(name, rating, directorName, runtime, year);
    System.out.println("Added movie(s) succesfully");
  } catch (Exception e) {
    System.out.println("Error: movie may not have been added");
  }
}

public void searchMoviesByRankFD(double y, double x) {
  try {
    
    System.out.println("Movies within range " + y +"-" + x +":");
    String movies = movieList.getMoviesByRating(y, x);
    if(movies.length()==0) {System.out.println("No such movie found");}else {
    System.out.println(movies);}
  } catch (Exception e) {
    System.out.println("Error when searching movies");
  }
}

public void printListFD() {
  try {
    System.out.println(movieList.printAllMovies());
  } catch (Exception e) {
    System.out.println("Error when searching movies");
  }
}
public void clearFD() {
  
  try {
    movieList.clear();
    System.out.println("Cleared movies succesfully");
  } catch (Exception e) {
    System.out.println("Error: movies may not have been cleared");
  }
  
}

public void printDataFD() {
  
  try {
    System.out.println(movieList.getData());
  } catch (Exception e) {
    System.out.println("Error: data  may not have been displayed");
  } 
  
}


}
