class_files = src/pub/libogame/*.java src/pub/darkmatterbot/*.java
#$(shell find -L src/ -iname "*.java")


# Create the bin directory to sort all the class files into
bin/ : $(class_files)
	mkdir -p bin
	javac -d bin $(class_files)

#Create a java archive file (JAR) for distribution
jar : bin
	jar cfev DarkMatterBot.jar pub.darkmatterbot.Main -C bin pub

# Run
run : bin/
	java -cp bin pub.darkmatterbot.Main

#Compile and run test on change of source files
onChange :
	while true; do inotifywait src/bup/*/*.java -e modify,moved_to; make run; done

# Clean up the mess:
clean :
	rm -Rf bin/
	rm -f DarkMatterBot.jar
