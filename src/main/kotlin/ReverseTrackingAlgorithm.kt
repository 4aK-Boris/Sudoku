class ReverseTrackingAlgorithm {

    fun solve(sudoku: Array<IntArray>): SudokuArray {
        val indexes = Indexes()
        val sudokuArray = SudokuArray(sudoku = sudoku)
        indexes.initialize(array = sudokuArray)
        return recursion(array = sudokuArray, indexes = indexes)
    }

    private fun recursion(array: SudokuArray, indexes: Indexes): SudokuArray {
        while (indexes.index != null) {
            when {
                array.isLast(index = indexes.index!!) -> {
                    array.reset(index = indexes.index!!)
                    indexes.previous()
                }
                array.increase(indexes.index!!) -> indexes.next()
                else -> indexes.previous()
            }
        }
        return array
    }

    companion object {
        private const val SIZE = 9
    }
}