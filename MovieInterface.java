public interface MovieInterface extends Comparable<MovieInterface> {
// public MovieInterface(String title, int released_year, int runtime, Double rating, String directorName);
    public String getTitle();
    public int getReleasedYear();
    public int getRuntime();
    public Double getRating();
    public String getDirectorName();
    public int compareTo(MovieInterface y);
    public int compareTo(double x);
}
