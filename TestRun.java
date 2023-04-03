public class TestRun {

	public static void main(String[] args) {
		RedBlackTreeBD<MovieInterface> RBTInstance = new RedBlackTreeBD<MovieInterface>();
		MovieReaderDW MovieReaderInstance = new MovieReaderDW();
		MovieRanking testBD = new MovieRanking(MovieReaderInstance, RBTInstance);
		FrontendFD testFD = new FrontendFD(testBD);
		testFD.runCommandLoop();
	}
}
