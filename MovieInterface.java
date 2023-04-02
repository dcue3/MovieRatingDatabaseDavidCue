public interface MovieInterface extends Comparable<MovieInterface>{
        // public MovieInterface(String title, int released_year, int runtime, Double rating, String directorName);
    //methods
    public String getTitle();
    public int getReleasedYear();
    public int getRuntime();
    public Double getRating();
    public String getDirectorName();
    public int compareTo(double rating);
    public int compareTo(MovieInterface movie);
}
