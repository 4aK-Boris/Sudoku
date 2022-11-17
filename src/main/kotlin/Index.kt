class Index(private val contains: (Int, Int) -> Boolean): BaseIndex() {

    override fun next() {
        while (contains(i, j)) {
            super.next()
        }
    }

    override fun previous() {
        while (contains(i, j)) {
            super.previous()
        }
    }

}