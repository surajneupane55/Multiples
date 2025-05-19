!/bin/bash

if [ "$#" -ne 2 ]; then
    echo "Usages: $0 inputFile.txt outputFile.txt, where outputFile is string file name to be created"
    exit 1
fi

inputFile=$1
outputFile=$2

if [ -e "$inputFile" ]; then
    echo "Input file exists"
else
    echo "Input file does not exist"
    exit 1
fi

if [ -e "$outputFile" ]; then
    echo "Output file exists"
else
    echo "Output file does not exist"
    exit 1
fi



#groovy MultiplesEngine.groovy input0.txt output0.txt