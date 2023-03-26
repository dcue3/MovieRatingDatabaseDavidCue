public class MovieDW implements MovieInterface{
	
	private String title;
	private int releasedYear;
	private int runtime;
	private double rating;
	private String director;
	
	public MovieDW(String title, int releasedYear, int runtime, double rating, String director) {
		this.title = title;
		this.releasedYear = releasedYear;
		this.runtime  = runtime;
		this.rating = rating;
		this.director = director;
	}
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public int getReleasedYear() {
		// TODO Auto-generated method stub
		return releasedYear;
	}

	@Override
	public int getRuntime() {
		// TODO Auto-generated method stub
		return runtime;
	}

	@Override
	public Double getRating() {
		// TODO Auto-generated method stub
		return rating;
	}

	@Override
	public String getDirectorName() {
		// TODO Auto-generated method stub
		return director;
	}
	
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

