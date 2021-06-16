run: Driver.class
	java Driver

Driver.class: Driver.java
	javac Driver.java

clean:
	rm -f *.class

