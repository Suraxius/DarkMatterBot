# Create the bin directory to sort all the class files into
bin : manifest src
	mkdir -p bin
	javac -d bin/ -s src/ @manifest

# Create a file that lists all the java files under src/ (used by javac)
manifest : $(shell find -L src/ -type f)
	find -L src/ -iname "*.java" > manifest

#Create a java archive file (JAR) for distribution
jar : bin
	jar cfev DarkMatterBot.jar pub.darkmatterbot.Main -C bin pub ../images

# Run
run : bin
	java -cp bin pub.darkmatterbot.Main

#Compile and run test on change of source files
onChange :
	while true; do inotifywait src/bup/*/*.java -e modify,moved_to; make test; done

# Clean up the mess:
clean :
	rm -f manifest
	rm -Rf bin/
	rm -f DarkMatterBot.jar
