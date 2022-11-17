import java.io.File

class Sudoku {

    private lateinit var sudokuArray: Array<IntArray>

    fun readFile(fileName: String) {
        val bufferedReader = File(fileName).bufferedReader()
        sudokuArray = bufferedReader.readLines().map { line ->
            line.split(" ").map { char -> char.toInt() }.toIntArray()
        }.toTypedArray()
    }

    fun solveWithReverseTrackingAlgorithm() {
        val reverseTrackingAlgorithm = ReverseTrackingAlgorithm()
        val result = reverseTrackingAlgorithm.solve(sudoku = sudokuArray)
        result?.forEach { array ->
            println(array.joinToString(" "))
        }
    }

}