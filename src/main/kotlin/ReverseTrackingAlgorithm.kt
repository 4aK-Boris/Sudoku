class ReverseTrackingAlgorithm {

    private val initialValues = mutableListOf<Pair<Int, Int>>()

    fun solve(sudoku: Array<IntArray>): Array<IntArray>? {
        createInitialValues(sudoku = sudoku)
        val first = firstElement() ?: return sudoku
        return recursion(sudoku = sudoku, i = first.first, j = first.second)
    }

    private fun recursion(sudoku: Array<IntArray>, i: Int, j: Int): Array<IntArray>? {
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

    private fun checkNumber(sudoku: Array<IntArray>, i: Int, j: Int, number: Int): Boolean {
        val flagHorizontal = containsHorizontal(sudoku, i, number)
        val flagVertical = containsVertical(sudoku, j, number)
        val flagSquare = containsSquare(sudoku, i, j, number)
        return flagHorizontal && flagVertical && flagSquare
    }

    private fun containsVertical(sudoku: Array<IntArray>, j: Int, number: Int): Boolean {
        return sudoku.count { array -> array[j] == number } != 0
    }

    private fun containsHorizontal(sudoku: Array<IntArray>, i: Int, number: Int): Boolean {
        return sudoku[i].contains(number)
    }

    private fun containsSquare(sudoku: Array<IntArray>, i: Int, j: Int, number: Int): Boolean {
        val indexI = (i % 3) * 3
        val indexJ = (j % 3) * 3
        val items = mutableListOf<Int>()
        for (n in indexI..indexI + 2) {
            for (m in indexJ..indexJ + 2) {
                items.add(sudoku[n][m])
            }
        }
        return items.contains(number)
    }

    private fun createInitialValues(sudoku: Array<IntArray>) {
        sudoku.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, count ->
                if (count != 0) initialValues.add(i to j)
            }
        }
    }

    private fun contains(i: Int, j: Int): Boolean {
        return initialValues.contains(i to j)
    }

    private fun next(i: Int, j: Int): Pair<Int, Int>? {
        var newJ = j + 1
        var newI = i
        if (newJ == SIZE) {
            newJ = 0
            newI++
            if (newI == SIZE) {
                newI = 0
            }
        }
        return when {
            newJ == 0 && newI == 0 -> null
            contains(newI, newJ) -> next(i = newI, j = newJ)
            else -> newI to newJ
        }
    }

    private fun previous(i: Int, j: Int): Pair<Int, Int>? {
        var newJ = j - 1
        var newI = i
        if (newJ == -1) {
            newJ = SIZE - 1
            newI--
            if (newI == -1) {
                newI = SIZE - 1
            }
        }
        return when {
            newJ == SIZE - 1 && newI == SIZE - 1 -> null
            contains(newI, newJ) -> previous(i = newI, j = newJ)
            else -> newI to newJ
        }
    }

    private fun firstElement(): Pair<Int, Int>? {
        return if (contains(i = 0, j = 0)) next(i = 0, j = 0)
        else 0 to 0
    }

    companion object {
        private const val SIZE = 9
    }
}