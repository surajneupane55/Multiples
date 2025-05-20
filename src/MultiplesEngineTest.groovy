class MultiplesEngineTest {
    static runTest() {
        cleanupTestData("sunshineTestFiles", "failingTestFiles", "outputTestFiles")

        createSunshineInputFiles()
        createFailingInputFiles()

        runForSunshineTests("sunshineTestFiles")
        //runForFailingTests("failingTestFiles")
    }

    private static runForFailingTests(String testFiles) {
        def folder = new File(testFiles)
        if (folder.exists()) {
            folder.eachFile { File file ->
                def inputFileName = file.getName()
                def outputFile = new File(inputFileName.replace("input", "output"))
                MultiplesEngine.getRawDataFileForProcessing file, outputFile
            }
        }
    }

    private static runForSunshineTests(String testFiles) {
        def folder = new File(testFiles)
        if (folder.exists()) {
            folder.eachFile { File file ->
                def inputFileName = file.getName()
                def outputFile = new File(inputFileName.replace("input", "output"))
                MultiplesEngine.getRawDataFileForProcessing file, outputFile
            }
        }
    }

    private static createSunshineInputFiles() {
        def folderName = "sunshineTestFiles"
        def folder = MultiplesEngine.createNewFolder(folderName)
        (0..0).each {
            def file = new File(folder, "input${it}.txt")
            getSunshineTestData(file)
        }
    }

    private static createFailingInputFiles() {
        def folderName = "failingTestFiles"
        def folder = MultiplesEngine.createNewFolder(folderName)
        (0..2).each {
            getFailingTestData(folder, it)
        }
    }

    private static void getSunshineTestData(File file) {
        def rangeAB = [1..9]
        def rangeC = [30..70]
        def randomRows = (int) (Math.random() * 10) + 1
        randomRows.times {
            def numberA = getRandomNumberForInputRange rangeAB
            def numberB = getRandomNumberForInputRange rangeAB
            def numberC = getRandomNumberForInputRange rangeC
            file.append("${numberA} ${numberB} ${numberC}" + "\n")
        }
    }

    private static void getFailingTestData(File folder, int fileIndex) {
        def rangeAB = [1..9]
        def rangeC = [30..70]
        def randomRows = (int) (Math.random() * 4) + 1
        randomRows.times {
            def numberA = getRandomNumberForInputRange rangeAB
            def numberB = getRandomNumberForInputRange rangeAB
            def numberC = getRandomNumberForInputRange rangeC
            def numberEdge = getRandomNumberForInputRange rangeAB
            switch (fileIndex) {
                case 0:
                    new File(folder, "numberWithFourRows.txt")
                            .append("${numberA} ${numberB} ${numberEdge} ${numberC}" + "\n")
                    break
                case 1:
                    new File(folder, "targetNumberIsLower.txt")
                            .append("${numberA} ${numberB} ${1}" + "\n")
                    break
                case 2:
                    new File(folder, "numberWithZeroValue.txt")
                            .append("${0} ${numberB} ${numberC}" + "\n")
                    break
                default:
                    new File(folder, "numberWithZeroValue.txt")
                            .append("${numberA} ${0} ${numberC}" + "\n")
            }
        }
    }

    private static getRandomNumberForInputRange(ArrayList<IntRange> range) {
        range.collect { it -> it.get((int) (Math.random() * it.size())) }.first()
    }

    private static cleanupTestData(String... folderNames) {
        folderNames.each { String folderName ->
            def folder = new File(folderName)
            if (folder.exists()) {
                try {
                    folder.eachFile { file ->
                        file.delete()
                    }
                } catch (Exception e) {
                    println "Error deleting files in $folderName: ${e.message}"
                }
                folder.deleteDir()
            }
        }
    }
}
