class Indexes {

    private val initialValues = mutableSetOf<Index>()

    fun initialize(array: SudokuArray) {

    }

    private fun contains(index: Index): Boolean {
        return initialValues.contains(index)
    }

    private fun firstElement(): Index {
        val index = Index(contains = ::contains)
        if (contains(index = index)) index.next()
        return index
    }

}