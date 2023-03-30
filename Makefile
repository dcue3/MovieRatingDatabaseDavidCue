runTests: BackendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

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
clean:
	rm *.class
