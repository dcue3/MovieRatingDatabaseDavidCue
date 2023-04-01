// --== CS400 Spring 2023 File Header Information ==--
// Name: Asish Das
// Email: das38@wisc.edu
// Team: RED(DF)
// TA: Callie
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * This class creates  a single movie object
 * Implements the MovieInterface
 */
public class MovieDW implements MovieInterface{
	//private fields
	private String title;
	private int releasedYear;
	private int runtime;
	private double rating;
	private String director;
	
	/**
	 *Contstuctor for the class
	 * @param title- title of the movie
	 * @param releasedYear - the released year of the movie
	 * @param runtime - length of the movie
	 * @param rating- rating of the movie
	 * @param director - director of the movie
	 */
	public MovieDW(String title, int releasedYear, int runtime, double rating, String director) {
		this.title = title;
		this.releasedYear = releasedYear;
		this.runtime  = runtime;
		this.rating = rating;
		this.director = director;
	}
	
	/**
	 *@returns the tile of the movie
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}
	
	/**
	 *@returns the released year of the movie
	 */
	@Override
	public int getReleasedYear() {
		// TODO Auto-generated method stub
		return releasedYear;
	}
	
	/**
	 *@returns the runtime of a movie
	 */
	@Override
	public int getRuntime() {
		// TODO Auto-generated method stub
		return runtime;
	}
	
	/**
	 *@returns the rating of the movie
	 */
	@Override
	public Double getRating() {
		// TODO Auto-generated method stub
		return rating;
	}
	
	/**
	 *@returns the director of the movie
	 */
	@Override
	public String getDirectorName() {
		// TODO Auto-generated method stub
		return director;
	}
	
	/**
	 *compares a movie with a rating
	 *@returns 1 if the rating of the movie is greater, -1 if lower, 0 otherwise.
	 */
	@Override
	public int compareTo(double rating) {
		if (this.rating > rating) {
			return 1;
		}
		
		else if(this.rating < rating) {
			return -1;
		}
		
		else {
			return 0;
		}
	}
	
	/**
	 * compares two movie object
	 * @returns 1 if the rating of the movie is greater, -1 if lower, 0 otherwise. 
	 */
	@Override
	public int compareTo(MovieInterface movie) {
		if (this.rating > movie.getRating()) {
			return 1;
		}
		
		else if(this.rating < movie.getRating()) {
			return -1;
		}
		
		else {
			return 0;
		}
	}
}

