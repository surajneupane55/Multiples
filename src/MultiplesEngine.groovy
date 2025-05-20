class MultiplesEngine {

    static void getRawDataFileForProcessing(File inputFile, File outputFile) {
        validateFiles(inputFile, outputFile)
        validateInputFileContent getInputListOfIntegers(inputFile)
        def multiplesMap = computeMultiples(getInputListOfIntegers(inputFile))
        writeToOutputFile multiplesMap, outputFile
    }

    private static void writeToOutputFile(Map mapMultiples, File outputFile) {
        mapMultiples.each { key, value ->
            def expectedFormat = "$key: ${value.join(' ')}"
            println expectedFormat
            appendResultToOutputFile expectedFormat, outputFile
        }
    }

    private static void appendResultToOutputFile(String result, File outputFile) {
        def targetFile = new File("outputTestFiles", outputFile.getName())
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
        collectionOfMultiple.sort { a, b ->
            a.key <=> b.key
        }
    }

    private static void validateInputFileContent(List<List<Integer>> inputListOfIntegers) {
        assert inputListOfIntegers.every {
            it.size() == 3
        }, "Input file must have 3 integers in each line."

        assert inputListOfIntegers.every {
            it.every {
                it >= 1
            }
        }, "Input file must have integers greater than or equal to 1."

        inputListOfIntegers.every { it ->
            assert it[0..-2].every { value ->
                value < it.last()
            }, "Input file target value should be greater than factorial."
        }
    }

    private static void validateFiles(File inputFile, File outputFile) {
        assert inputFile.exists() && inputFile.canRead(),
                "Input file does not exist or is not readable."
        if (outputFile.exists()) {
            outputFile.delete()
        } else {
            def outboundFolder = createNewFolder("outputTestFiles")
            def targetFile = new File(outboundFolder, outputFile.getName())
            if (!targetFile.exists()) {
                targetFile.createNewFile()
            }
        }
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