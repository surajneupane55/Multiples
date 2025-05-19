static void main(String[] args) {
    cleanupTestData("testFilesSunshine", "testFilesEdgeCases") //remove before submitting
    createSunshineInputFilesForTesting()
    createEdgeCasesInputFilesForTesting()
    //cleanupTestData("testFilesSunshine", "testFilesEdgeCases")
}

private static createSunshineInputFilesForTesting() {
    def folderName = "testFilesSunshine"
    def folder = createNewFolder(folderName)
    (0..9).each {
        def file = new File(folder, "file${it}.txt")
        getSunshineTestDataToWriteInAInputFile(file)
    }
}

private static createEdgeCasesInputFilesForTesting() {
    def folderName = "testFilesEdgeCases"
    def folder = createNewFolder(folderName)
    (0..10).each {
        def file = new File(folder, "file${it}.txt")
        getEdgeCaseTestDataToWriteInAInputFile(file)
    }
}

private static void getSunshineTestDataToWriteInAInputFile(File file) {
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

private static void getEdgeCaseTestDataToWriteInAInputFile(File file) {
    def rangeAB = [1..9]
    def rangeC = [30..70]
    def randomRows = (int) (Math.random() * 10) + 1
    randomRows.times {
        def numberA = getRandomNumberForInputRange rangeAB
        def numberB = getRandomNumberForInputRange rangeAB
        def numberC = getRandomNumberForInputRange rangeC
        def numberEdge = getRandomNumberForInputRange rangeAB
        def randomEdgeCase = new Random().nextInt(3)
        switch (randomEdgeCase) {
            case 0:
                file.append("${numberA} ${numberB} ${numberEdge} ${numberC}" + "\n")
                break
            case 1:
                file.append("${numberA} ${numberB} ${1}" + "\n")
                break
            case 2:
                file.append("${0} ${numberB} ${numberC}" + "\n")
                break
            default:
                file.append("${numberA} ${numberB} ${numberC}" + "\n")
        }
    }
}

private static getRandomNumberForInputRange(ArrayList<IntRange> range) {
    range.collect { it -> it.get((int) (Math.random() * it.size())) }.first()
}

private static File createNewFolder(String folderName) {
    def folder = new File(folderName)
    folder.exists() ? folder.delete() : folder.mkdir()
    folder
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