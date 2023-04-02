
public class MovieBD implements MovieInterface {

	String title;
	int released_year;
	int runtime;
	double rating;
	String directorName;
	
	public MovieBD(String title, int released_year, int runtime, Double rating, String directorName) {
		this.title = title;
		this.released_year = released_year;
		this.runtime = runtime;
		this.rating = rating;
		this.directorName = directorName;
	}
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	@Override
	public int getReleasedYear() {
		// TODO Auto-generated method stub
		return this.released_year;
	}

	@Override
	public int getRuntime() {
		// TODO Auto-generated method stub
		return this.runtime;
	}

	@Override
	public Double getRating() {
		// TODO Auto-generated method stub
		return this.rating;
	}

	@Override
	public String getDirectorName() {
		// TODO Auto-generated method stub
		return this.directorName;
	}

	@Override
	public int compareTo(double x) {
		if ((double)x == (double)this.getRating()) {
			return 0;
		}
		if ((double)x < (double)this.getRating()) {
			return 1;
		}
		return -1;
	}
	
	@Override
	public int compareTo(MovieInterface y) {
		if ((y == null) && (this.getRating() < 0)) {
			return -1000;
		}
		if ((double) y.getRating() == (double) this.getRating()) {
			return 0;
		}
		if ((double) y.getRating() < (double) this.getRating()) {
			return 1;
		}
		return -1;
	}


}
