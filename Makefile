<<<<<<< HEAD
runFrontendDeveloperTests: FrontendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests

runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java CreateAll
	javac -cp .:junit5.jar FrontendDeveloperTests.java

CreateAll: FrontendFD.java FrontendInterface.java MovieFD.java MovieInterface.java MovieRankingFD.java MovieRankingInterface.java
	javac FrontendFD.java
	javac FrontendInterface.java
	javac MovieFD.java
	javac MovieInterface.java
	javac MovieRankingFD.java
	javac MovieRankingInterface.java

=======
runTests: BackendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests
>>>>>>> parent of b7ba547 (data wrangler merged)

clean:
	rm *.class
