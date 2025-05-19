class MultiplesEngine {

    static void getRawDataFileForProcessing(File inputFile, String outputFileName) {
        validateInputFile getInputListOfIntegers(inputFile)
        computeMultiples(getInputListOfIntegers(inputFile), outputFileName)
    }

    private static void computeMultiples(List<List<Integer>> inputListOfIntegers, String outputFileName) {

    }

    private static void validateInputFile(List<List<Integer>> inputListOfIntegers) {
        assert inputListOfIntegers.every {
            it.size() == 3
        }, "Input file must have 3 integers in each line."

        assert inputListOfIntegers.every {
            it.every {
                it >= 1
            }
        }, "Input file must have integers greater than or equal to 1."
    }

    private static List<List<Integer>> getInputListOfIntegers(File inputFile) {
        inputFile.readLines().collect {
            it.split(' ').collect { it.toInteger() }
        }
    }
}