
public class MovieFD implements MovieInterface{

  private String title;
  private int year; 
  private int runtime;
  private double rat;
  public String dir;
  
  public MovieFD(String title, int released_year, int runtime, Double rating, String directorName) {
    this.title=title;year=released_year;this.runtime=runtime;rat=rating;dir=directorName;
  }
  
  @Override
  public String getTitle() {
    // TODO Auto-generated method stub
    return title;
  }

  @Override
  public int getReleasedYear() {
    // TODO Auto-generated method stub
    return year;
  }

  @Override
  public int getRuntime() {
    // TODO Auto-generated method stub
    return runtime;
  }

  @Override
  public Double getRating() {
    // TODO Auto-generated method stub
    return rat;
  }

  @Override
  public String getDirectorName() {
    // TODO Auto-generated method stub
    return dir;
  }

  @Override
  public int compareTo(MovieInterface movie) {
    if(this.getTitle().equals(movie.getTitle()))
    return 0;
    else
      return 1;
  }

  @Override
  public int compareTo(double rating) {
    // TODO Auto-generated method stub
    return 0;
  }

}
