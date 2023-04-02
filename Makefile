runFrontendDeveloperTests: FrontendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java CreateAll
	javac -cp .:junit5.jar FrontendDeveloperTests.java

CreateAll: FrontendFD.java FrontendInterface.java MovieFD.java MovieInterface.java MovieRankingFD.java MovieRankingInterface.java
	javac FrontendFD.java
	javac FrontendInterface.java
	javac MovieFD.java
	javac MovieInterface.java
	javac MovieRankingFD.java
	javac MovieRankingInterface.java

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
