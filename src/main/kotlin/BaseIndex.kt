open class BaseIndex {

    var i = 0
        private set

    var j = 0
        private set

    open fun next() {
        j++
        if (j == SIZE) {
            j = 0
            i++
            if (i == SIZE) i = 0
        }
    }

    open fun previous() {
        j--
        if (j == -1) {
            j = SIZE - 1
            i--
            if (i == -1) i = SIZE - 1
        }
    }
}