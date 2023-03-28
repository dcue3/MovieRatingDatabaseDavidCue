runTests: DataWranglerTests.class
	java -jar junit5.jar -cp . --select-class=DataWranglerTests

DataWranglerTests.class: DataWranglerTests.java MovieReaderDW.class MovieDW.class
	javac -cp .:junit5.jar DataWranglerTests.java

MovieReaderDW.class: MovieReaderDW.java MovieReaderInterface.java
	javac MovieReaderDW.java MovieReaderInterface.java

MovieDW.class: MovieDW.java MovieInterface.java
	javac MovieDW.java MovieInterface.java

clean:
	rm *.class
