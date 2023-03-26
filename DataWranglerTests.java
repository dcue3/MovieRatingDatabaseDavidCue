import java.io.FileNotFoundException;
import java.util.List;

public class DataWranglerTests {
	public static void main(String[] args) {
		MovieReaderInterface some = new MovieReaderDW();
		try {
			List<MovieInterface> posts =some.readMoviesFromFile("csv.txt");
			System.out.println("A");
			System.out.println(posts.get(0).getTitle());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

