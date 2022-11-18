data class Index(val i: Int, val j: Int) {

    fun getSudokuItem(sudokuArray: Array<IntArray>) = sudokuArray[i][j]
}