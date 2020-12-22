Flow-size sketches

For a description on the project, kindly refer to project3.pdf

Description on the files and how to run them:

There are 3 java files, one for each type of Flow-Size sketch.
1. countmin.java for Count Min
2. csketch.java for Counter Sketch 
3. activecounter.java for Active Counter

There are 3 text files, one for each algorithm's output (Note: These text files will be overwritten each time you run the code)
1. countmin.txt for Count Min
2. csketch.txt for Counter Sketch 
3. activecounter.txt for Active Counter 

There is one README.md file
There is one input file - project3input.txt

To run the programs:
1. To compile the java program, write "javac <file_name>.java". For instance, "javac csketch.java" for the Counter Sketch program.
2. To run the program, write "java <file_name>".  For instance, "java csketch" for the Counter Sketch program.
3. When you run it, you will be asked to enter the input based on the program you are running.
   So the number of flows, k and w for Count Min and Counter Sketch 
   and the size of the active counter for the Active Counter program
4. Once you enter that, the program will execute and the output will be added to a text file (for example: csketch.txt for Counter Sketch).
5. The output file contains the required output depending on the program you are executing.
	 For instance, the final value of the Active Counter in decimal for the Active Counter program.
6. Repeat the above process for all three algorithms.
