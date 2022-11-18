import java.io.File

class Sudoku {

    fun solve(sudokuArray: Array<IntArray>): Array<IntArray> {
        val reverseTrackingAlgorithm = ReverseTrackingAlgorithm()
        val result = reverseTrackingAlgorithm.solve(sudoku = sudokuArray)
        return result.getArray()
    }
}