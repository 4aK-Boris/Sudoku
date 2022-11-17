fun main() {
    val sudoku = Sudoku()
    sudoku.readFile(fileName = "sudoku.txt")
    sudoku.solveWithReverseTrackingAlgorithm()
}