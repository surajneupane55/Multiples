

**User Story**

_As a user I should be able to input the file as first argument. 
The file should contain 3 numbers per row seperated by space. 
There can be any number of rows in a file.
The A and B are the first two numbers in a row simultaneously.
The third number is a goal number.
The system should find all the multiples of number A and B below the goal number in ascending order.
The system should print the output and also write it to the target output file._ 

**Test Discovery**

_This test will automate the creation of files with random given structure._
1. Reject files that do not match the expected structure.
2. Handle errors gracefully.
3. Log the processing of files.
4. Write the output to a target file.

**Test Case**

1. Given the different structures of files is provided to the system
2. When the system processes the files
3. Then the system checks if the files matches the expected structure
4. And the system throws an error if the file does not match the expected structure
5. And the system processes the file if it matches the expected structure
6. And the system returns the result of processed file as output logs
7. And the system prints the result in the output files **
