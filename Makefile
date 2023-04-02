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


clean:
	rm *.class
