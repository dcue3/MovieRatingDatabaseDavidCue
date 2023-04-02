runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar -cp . --select-class=DataWranglerTests

BackendDeveloperTests.class: BackendDeveloperTests.java MovieRanking.class MovieBD.class MovieReaderBD.class RedBlackTreeBD.class
	javac -cp .:junit5.jar BackendDeveloperTests.java

MovieRanking.class: MovieRanking.java MovieRankingInterface.java
	javac MovieRanking.java MovieRankingInterface.java

MovieBD.class: MovieBD.java MovieInterface.java
	javac MovieBD.java MovieInterface.java

MovieReaderBD.class: MovieReaderBD.java MovieReaderInterface.java
	javac MovieReaderBD.java MovieReaderInterface.java

RedBlackTreeBD.class: RedBlackTreeBD.java RedBlackTreeInterface.java
	javac RedBlackTreeBD.java RedBlackTreeInterface.java

DataWranglerTests.class: DataWranglerTests.java MovieReaderDW.class MovieDW.class
	javac -cp .:junit5.jar DataWranglerTests.java

MovieReaderDW.class: MovieReaderDW.java MovieReaderInterface.java
	javac MovieReaderDW.java MovieReaderInterface.java

MovieDW.class: MovieDW.java MovieInterface.java
	javac MovieDW.java MovieInterface.java

clean:
	rm *.class
