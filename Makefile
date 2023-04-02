runFrontendDeveloperTests: FrontendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests

runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar -cp . --select-class=DataWranglerTests

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar -cp . --select-class=AlgorithmEngineerTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java CreateAll
	javac -cp .:junit5.jar FrontendDeveloperTests.java

BackendDeveloperTests.class: BackendDeveloperTests.java MovieRanking.class MovieBD.class MovieReaderBD.class RedBlackTreeBD.class
	javac -cp .:junit5.jar BackendDeveloperTests.java

DataWranglerTests.class: DataWranglerTests.java MovieReaderDW.class MovieDW.class
	javac -cp .:junit5.jar DataWranglerTests.java

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java RedBlackTreeAE.class RedBlackTree.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

RedBlackTreeAE.class: RedBlackTreeAE.java RedBlackTreeInterface.java
	javac RedBlackTreeInterface.java
	javac RedBlackTreeAE.java 

RedBlackTree.class: RedBlackTree.java 
	javac RedBlackTree.java


MovieReaderDW.class: MovieReaderDW.java MovieReaderInterface.java
	javac MovieReaderDW.java MovieReaderInterface.java

MovieDW.class: MovieDW.java MovieInterface.java
	javac MovieDW.java MovieInterface.java

MovieRanking.class: MovieRanking.java MovieRankingInterface.java
	javac MovieRanking.java MovieRankingInterface.java

MovieBD.class: MovieBD.java MovieInterface.java
	javac MovieBD.java MovieInterface.java

MovieReaderBD.class: MovieReaderBD.java MovieReaderInterface.java
	javac MovieReaderBD.java MovieReaderInterface.java

RedBlackTreeBD.class: RedBlackTreeBD.java RedBlackTreeInterface.java
	javac RedBlackTreeBD.java RedBlackTreeInterface.java


CreateAll: FrontendFD.java FrontendInterface.java MovieFD.java MovieInterface.java MovieRankingFD.java MovieRankingInterface.java
	javac FrontendFD.java
	javac FrontendInterface.java
	javac MovieFD.java
	javac MovieInterface.java
	javac MovieRankingFD.java
	javac MovieRankingInterface.java

clean:
	rm *.class
