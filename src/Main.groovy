import java.nio.file.Files

static void main(String[] args) {
    createInputFilesWithDifferentPatternsForTesting()
}

private static createInputFilesWithDifferentPatternsForTesting() {
    List<Files> testFiles = []
    [0..9].each { i ->
        def file = new File("file${i}.txt")
        file << getRandomTestDataToWriteInAInputFile()
        testFiles.push(file as Files)
    }
}

private static String getRandomTestDataToWriteInAInputFile() {
    def rangeAB = [1..9]
    def rangeC = [30..70]
    def randomRows = (int) (Math.random() * 10) + 1
    randomRows.each {
        def numberA = getRandomNumberForInputRange rangeAB
        def numberB = getRandomNumberForInputRange rangeAB
        def numberC = getRandomNumberForInputRange rangeC
        //to be continued....WIP
    }
}

private static getRandomNumberForInputRange(ArrayList<IntRange> range) {
    range.collect { it -> it.get((int) (Math.random() * it.size())) }
}