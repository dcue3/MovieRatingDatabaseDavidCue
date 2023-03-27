import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieReaderDW implements MovieReaderInterface {
        

	/**
	 * Method for creating movie objects from csv file which contains movie interfaces.
	 *@Param- filename- the name of the file to be parsed.
	 */
	@Override
	public List<MovieInterface> readMoviesFromFile(String filename) throws FileNotFoundException {
		//creating a list to store the movie objects.
		ArrayList<MovieInterface> movies = new ArrayList<>();
		Scanner in = new Scanner(new File(filename));
		while (in.hasNextLine()) {
			// each line in the file being read
			String line = in.nextLine();
			// getting all the parts
			//counting even number of quotes
			String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			//creating and adding new movie objects into the list
			movies.add(new MovieDW(parts[0], Integer.valueOf(parts[1]), Integer.valueOf(parts[2].split(" ")[0]), Double.valueOf(parts[3]), parts[4]));
		}
		//closing the scanner
		in.close();
		return movies;//returning the list of Movie objects.
	}
}

