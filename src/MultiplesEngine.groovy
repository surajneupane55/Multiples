class MultiplesEngine {
    private static final String OUTPUT_DIR = "outputTestFiles"


    static void getRawDataFileForProcessing(File inputFile, File outputFile) {
        validateFiles(inputFile, outputFile)
        validateInputFileContent getInputListOfIntegers(inputFile)
        def multiplesMap = computeMultiples(getInputListOfIntegers(inputFile))
        writeToOutputFile multiplesMap, outputFile
    }

    private static void writeToOutputFile(Map mapMultiples, File outputFile) {
        mapMultiples.each { key, value ->
            def expectedFormat = "$key:${value.join(' ')}"
            println expectedFormat
            appendResultToOutputFile expectedFormat, outputFile
        }
    }

    private static void appendResultToOutputFile(String result, File outputFile) {
        def targetFile = new File(OUTPUT_DIR, outputFile.getName())
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
            collectionOfMultiple[key] = values
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
            }, "Input file target value [C] should be greater than numbers[A B] in [A B C]."
        }
    }

    private static void validateFiles(File inputFile, File outputFile) {
        assert inputFile.exists() && inputFile.canRead(),
                "Input file does not exist or is not readable."
        def targetFile = new File(OUTPUT_DIR, outputFile.getName())
        if (targetFile.exists()) {
            targetFile.delete()
        } else {
            def outboundFolder = createNewFolder(OUTPUT_DIR)
            targetFile.createNewFile()
        }
    }

    private static List<List<Integer>> getInputListOfIntegers(File inputFile) {
        inputFile.readLines().collect {
            it.split(' ').collect { it.toInteger() }
        }
    }

    static File createNewFolder(String folderName) {
        def folder = new File(folderName)
        if (!folder.exists()) {
            folder.mkdir()
        }
        folder
    }
}