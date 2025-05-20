static void main(String[] args) {

    if (args.length < 2 ) {
        println "Please provide the input and output file."
        System.exit(1)
    }
    def inputFile = new File(args[0])
    def outputFile = new File(args[1])
    MultiplesEngine.getRawDataFileForProcessing(inputFile, outputFile)
    //MultiplesEngineTest.runTest() for testing purposes

}