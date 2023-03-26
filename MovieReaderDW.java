import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieReaderDW implements MovieReaderInterface {

	@Override
	public List<MovieInterface> readMoviesFromFile(String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<MovieInterface> movies = new ArrayList<>();
		Scanner in = new Scanner(new File(filename));
		while (in.hasNextLine()) {
			// each line in the file being read
			String line = in.nextLine();
			// getting all the parts
			//counting even number of quotes
			String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			System.out.println(parts[0]);
			movies.add(new MovieDW(parts[0], Integer.valueOf(parts[1]), Integer.valueOf(parts[2].split(" ")[0]), Double.valueOf(parts[3]), parts[4]));
		}
		in.close();
		return movies;
	}
}

