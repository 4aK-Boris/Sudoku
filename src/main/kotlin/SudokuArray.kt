class SudokuArray(private val sudoku: Array<IntArray>) {

    fun contains(index: Index): Boolean {
        return index.getSudokuItem(sudokuArray = sudoku) == 0
    }

    fun getArray() = sudoku

    fun get(index: Index) = sudoku[index.i][index.j]

    fun isLast(index: Index) = get(index = index) == LAST

    fun reset(index: Index) {
        sudoku[index.i][index.j] = 0
    }

    fun increase(index: Index): Boolean {
        val first = get(index = index) + 1
        for (number in first..LAST) {
            if (!checkNumber(index = index, number)) {
                sudoku[index.i][index.j] = number
                return true
            }
        }
        sudoku[index.i][index.j] = 0
        return false
    }

    private fun checkNumber(index: Index, number: Int): Boolean {
        val flagHorizontal = containsHorizontal(index.i, number)
        val flagVertical = containsVertical(index.j, number)
        val flagSquare = containsSquare(index, number)
        return flagHorizontal || flagVertical || flagSquare
    }

    private fun containsVertical(j: Int, number: Int): Boolean {
        return sudoku.count { array -> array[j] == number } != 0
    }

    private fun containsHorizontal(i: Int, number: Int): Boolean {
        return sudoku[i].contains(number)
    }

    private fun containsSquare(index: Index, number: Int): Boolean {
        val indexI = (index.i / SIZE) * SIZE
        val indexJ = (index.j / SIZE) * SIZE
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
        private const val LAST = 9
    }
}