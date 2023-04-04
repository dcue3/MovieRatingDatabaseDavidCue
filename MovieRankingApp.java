public class MovieRankingApp {

        public static void main(String[] args) {
		// Creating instance of RedBlackTreeAE 
                RedBlackTreeAE<MovieInterface> RBTInstance = new RedBlackTreeAE<MovieInterface>();
                // Creating instance of MovieReaderDW
		MovieReaderDW MovieReaderInstance = new MovieReaderDW();
                // Creating instance of MovieRanking from Backend developer using instances of DW and AE classes above as the arguments for the MovieRanking's constructor
		MovieRanking MovieRankingInstance = new MovieRanking(MovieReaderInstance, RBTInstance);
                // Creating instane of FrontendFD with the Backend developer's MovieRanking object instantiated above as an argument for constructor
		FrontendFD runAppFrontend = new FrontendFD(MovieRankingInstance);
                // Running the command loop of the front end using the runCommandLoop() method which will run the app
		runAppFrontend.runCommandLoop();
        }
}
