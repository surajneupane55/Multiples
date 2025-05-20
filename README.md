

**User Story**

_As a user I should be able to input the file as first argument. 
The file should contain 3 numbers per row seperated by space. 
There can be any number of rows in a file.
The A and B are the first two numbers in a row simultaneously.
The third number is a goal number.
The system should find all the multiples of number A and B below the goal number in ascending order.
The system should print the output and also write it to the target output file._ 

**Test Discovery**

_This system will have the following capabilities._
1. Reject files that do not match the expected structure.
2. Handle errors gracefully.
3. Log the processing of files.
4. Write the output file to a target folder.

**Test Case**

1. Given the different structures of files is provided to the system
2. When the system processes the files
3. Then the system checks if the files matches the expected structure
4. And the system throws an error if the file does not match the expected structure
5. And the system processes the file if it matches the expected structure
6. And the system returns the result of processed file as output in console logs
7. And the system prints the result in the output file in output folder**


** HOW TO RUN THE CODE **

1. Clone the repository

2. Goto the directory:
   cd src  *Everything will be run from the src directory

3. Install the groovy in your system:
   macOS: brew install groovy

4. Compile the code
   groovyc Main.groovy

5. Run the code:
   Example:
   groovy -cp . Main sunshineTestFiles/input0.txt outputTestFiles/output0.txt

6. Check the output file in the outputTestFiles folder, you can always alter the value of input0 
   file to test the code with different inputs.

