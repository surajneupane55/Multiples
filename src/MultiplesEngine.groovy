class MultiplesEngine {

    static void getRawDataFileForProcessing(File inputFile, File outputFile) {
        validateInputFile getInputListOfIntegers(inputFile)
        def multiplesMap = computeMultiples(getInputListOfIntegers(inputFile))
        createOutputResult multiplesMap, outputFile
    }

    private static void createOutputResult(Map mapMultiples, File outputFile) {
        mapMultiples.each { key, value ->
            def expectedFormat = "$key: ${value.join(' ')}"
            println expectedFormat
            appendResultToOutputFile  expectedFormat, outputFile
        }
    }

    private static void appendResultToOutputFile(String result, File outputFile) {
        def folder = createNewFolder("outputTestFiles")
        def targetFile = new File(folder, outputFile.getName())
        if (!targetFile.exists()) {
            targetFile.createNewFile()
        }
        targetFile.append result + "\n"
    }

    private static Map computeMultiples(List<List<Integer>> inputListOfIntegers) {
        Map collectionOfMultiple = [:]
        inputListOfIntegers.each { list ->
            def numA = list[0]
            def numB = list[1]
            def numC = list[2]
            def numAMultiple = (numA..numC).findAll { it ->
                it % numA == 0
            }
            def numBMultiple = (numB..numC).findAll() { it ->
                it % numB == 0
            }
            def key = list.last()
            def values = (numAMultiple + numBMultiple).unique().sort().findAll {
                it != key
            }
            collectionOfMultiple.putLast key, values
        }
        collectionOfMultiple
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

    static File createNewFolder(String folderName) {
        def folder = new File(folderName)
        folder.exists() ? folder.delete() : folder.mkdir()
        folder
    }
}