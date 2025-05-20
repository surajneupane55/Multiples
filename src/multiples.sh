!/bin/bash

if [ "$#" -ne 2 ]; then
    echo "Usages: $0 inputFile.txt outputFile.txt, where outputFile is file name to be created in outputTestFiles folder"
    exit 1
fi

inputFile=$1
outputFile=$2

[ -e "$inputFile" ] || { echo "Input file doesn't exist"; exit 1; }
[ -e "$outputFile" ] || { echo "Input file doesn't exist"; exit 1; }

echo "Input and outputFile exists"

groovy MultiplesEngine.groovy input0.txt output0.txt