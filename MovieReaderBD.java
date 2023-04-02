import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MovieReaderBD implements MovieReaderInterface {

	@Override
	public List<MovieInterface> readMoviesFromFile(String filename) throws FileNotFoundException {
		if (filename.equals("ShouldWork")) {
			MovieBD test1 = new MovieBD("test movie 1", 0, 1, (double)1, null);
			MovieBD test2 = new MovieBD("test movie 1", 0, 2, (double)2, null);
			MovieBD test3 = new MovieBD("test movie 1", 0, 3, (double)3, null);
			MovieBD test4 = new MovieBD("test movie 1", 0, 4, (double)4, null);
			MovieBD test5 = new MovieBD("test movie 1", 0, 5, (double)5, null);
			ArrayList<MovieInterface> toReturn = new ArrayList<>();
			toReturn.add(test1);
			toReturn.add(test2);
			toReturn.add(test3);
			toReturn.add(test4);
			toReturn.add(test5);
			return toReturn;
		}
		else {
			throw new FileNotFoundException();
		}
	}

}
