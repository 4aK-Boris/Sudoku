class Index(private val i: Int = 0, private val j: Int = 0) {

    val next: Index?
        get() {
            var newJ = j + 1
            var newI = i
            if (j == SIZE) {
                newJ = 0
                newI = i + 1
                if (i == SIZE) newI = 0
            }
            return //Index(i = newI, j = newJ)
        }

    previous

    fun previous() {
        j--
        if (j == -1) {
            j = SIZE - 1
            i--
            if (i == -1) i = SIZE - 1
        }
    }
}