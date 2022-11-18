class Indexes {

    private val initialValues = mutableListOf<Index>()

    fun initialize(array: SudokuArray) {
        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                Index(i = i, j = j).let {
                    if (array.contains(it)) initialValues.add(it)
                }
            }
        }
        index = initialValues.first()
    }

    fun next() {
        val k = initialValues.indexOf(index)
        index = initialValues.getOrNull(k + 1)
    }

    fun previous() {
        val k = initialValues.indexOf(index)
        index = initialValues.getOrNull(k - 1)
    }

    var index: Index? = null
        private set

    companion object {
        private const val SIZE = 9
    }
}