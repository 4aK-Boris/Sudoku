class SudokuArray(private val sudoku: Array<IntArray>) {

    val numbersIndex: Set<Index>
        get() {
            val set = mutableSetOf<Index>()
            for (i in 0 until MAIN_SIZE) {
                for (j in 0 until MAIN_SIZE) {
                    if (sudoku[i][j] == 0) set.add(sudoku[i][j])
                }
            }
        }

    private fun checkNumber(i: Int, j: Int, number: Int): Boolean {
        val flagHorizontal = containsHorizontal(i, number)
        val flagVertical = containsVertical(j, number)
        val flagSquare = containsSquare(i, j, number)
        return flagHorizontal || flagVertical || flagSquare
    }

    private fun containsVertical(j: Int, number: Int): Boolean {
        return sudoku.count { array -> array[j] == number } != 0
    }

    private fun containsHorizontal(i: Int, number: Int): Boolean {
        return sudoku[i].contains(number)
    }

    private fun containsSquare(i: Int, j: Int, number: Int): Boolean {
        val indexI = (i % SIZE) * SIZE
        val indexJ = (j % SIZE) * SIZE
        val items = mutableSetOf<Int>()
        for (n in indexI until indexI + SIZE) {
            for (m in indexJ until indexJ + SIZE) {
                items.add(sudoku[n][m])
            }
        }
        return items.contains(number)
    }

    companion object {
        private const val SIZE = 3
        private const val MAIN_SIZE = 9
    }
}