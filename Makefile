# 201120441 204616031 
# bennoam benshan2
compile: bin
	find src | grep .java > files.txt 
	javac -d bin -cp biuoop-1.4.jar @files.txt
	rm files.txt
	
run:
	java -cp biuoop-1.4.jar:bin Ass6Game
	
bin:
	mkdir bin

jar:
	jar cfm ass6game.jar Manifest.mf -C bin . -C resources .
	
check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*.java src/animations/*.java src/environment/*.java 
	src/game/*.java src/gameLevels/*.java src/geometry/*.java src/leveldevelopment/*.java src/listeners/*.java
	src/sprites/*.java