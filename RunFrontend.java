
public class RunFrontend {


public static void main(String args[]) {
  
  MovieReaderInterface movieLoader = new MovieReaderDW();
  RedBlackTreeInterface tree = new RedBlackTreeAE();
  
  MovieRankingInterface mv = new MovieRanking(movieLoader,tree);
  FrontendFD frontend = new FrontendFD(mv);
  frontend.runCommandLoop();
  
  
}
  
}
