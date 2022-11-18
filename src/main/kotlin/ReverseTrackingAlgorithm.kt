class ReverseTrackingAlgorithm {

    private val initialValues = mutableListOf<Pair<Int, Int>>()

    fun solve(sudoku: Array<IntArray>): Array<IntArray>? {
        val indexes = Indexes()
        val array = SudokuArray(sudoku = sudoku)
        return recursion(array = array, index = indexes.)
    }

    private fun recursion(array: SudokuArray, index: Index) {
        println("i = $i, j = $j")
        if (sudoku[i][j] == SIZE) {
            sudoku[i][j] = 0
            val prev = previous(i, j) ?: return null
            return recursion(sudoku, prev.first, prev.second)
        }
        var number = sudoku[i][j] + 1
        while (number < SIZE && checkNumber(sudoku, i, j, number)) {
            number++
        }
        println(number)
        if (checkNumber(sudoku, i, j, number)) {
            val prev = previous(i, j) ?: return null
            sudoku[i][j] = 0
            return recursion(sudoku, prev.first, prev.second)
        } else {
            val next = next(i, j) ?: return sudoku
            sudoku[i][j] = number
            return recursion(sudoku, next.first, next.second)
        }
    }
    companion object {
        private const val SIZE = 9
    }
}