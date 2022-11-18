import org.junit.Before
import org.junit.Test
import java.io.File

class SudokuArrayTest {

    private fun readFile(fileName: String): Array<IntArray> {
        val bufferedReader = File(fileName).bufferedReader()
        return bufferedReader.readLines().map { line ->
            line.split(" ").map { char -> char.toInt() }.toIntArray()
        }.toTypedArray()
    }

    @Before
    fun before() {
        repeat(3) { index ->
            val fileName = "sudoku${index + 1}.txt"
            val array = readFile(fileName = fileName)
            val sudokuArray = SudokuArray(sudoku = array)
        }
    }

    @Test
    fun testVertical() {

    }
}