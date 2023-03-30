
public interface MovieInterface {
// public MovieInterface(String title, int released_year, int runtime, Double rating, String directorName);
    public String getTitle();
    public int getReleasedYear();
    public int getRuntime();
    public Double getRating();
    public String getDirectorName();
    public int compareTo(MovieInterface movie);
    public int compareTo(double rating);
}
