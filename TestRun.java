public class TestRun {

	public static void main(String[] args) {
		RedBlackTreeAE<MovieInterface> RBTInstance = new RedBlackTreeAE<MovieInterface>();
		MovieReaderDW MovieReaderInstance = new MovieReaderDW();
		MovieRanking testBD = new MovieRanking(MovieReaderInstance, RBTInstance);
		FrontendFD testFD = new FrontendFD(testBD);
		testFD.runCommandLoop();
	}
}
