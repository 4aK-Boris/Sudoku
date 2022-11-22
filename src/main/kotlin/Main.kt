import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import java.io.File

private const val SIZE = 9
private const val SIZE_ITEM = 3

@Composable
@Preview
fun App() {
    val array = createTextArray()

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(height = 32.dp))

            Box(
                modifier = Modifier.border(border = BorderStroke(width = 3.dp, color = Color.Black)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    array.forEachIndexed { i, mutableStates ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            mutableStates.forEachIndexed { j, mutableState ->
                                SudokuTextField(text = mutableState.value, modifier = Modifier) { text ->

                                    mutableState.value = text
                                }

                                if (j != SIZE - 1 && j % SIZE_ITEM != 2) {
                                    Spacer(
                                        modifier = Modifier.width(width = 1.dp).height(height = 64.dp)
                                            .background(color = Color.Gray)
                                    )
                                } else if (j % SIZE_ITEM == 2) {
                                    Spacer(
                                        modifier = Modifier.width(width = 3.dp).height(height = 64.dp)
                                            .background(color = Color.Black)
                                    )
                                }
                            }
                        }

                        if (i != SIZE - 1 && i % SIZE_ITEM != 2) {
                            Spacer(
                                modifier = Modifier.height(height = 1.dp).width(width = 588.dp)
                                    .background(color = Color.Gray)
                            )
                        } else if (i % SIZE_ITEM == 2) {
                            Spacer(
                                modifier = Modifier.width(width = 588.dp).height(height = 3.dp)
                                    .background(color = Color.Black)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(height = 32.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width(width = 588.dp)
            ) {

                Button(onClick = {
                    val sudokuArray = array.map { strings ->
                        strings.map { it.value.toInt() }.toIntArray()
                    }.toTypedArray()
                    val sudoku = Sudoku()
                    val result = sudoku.solve(sudokuArray = sudokuArray)
                    result.forEachIndexed { i, ints ->
                        ints.forEachIndexed { j, number ->
                            array[i][j].value = number.toString()
                        }
                    }
                }) {
                    Text(text = "Решить судоку")
                }

                Button(onClick = {
                    val sudokuArray = readFile(fileName = "sudoku3.txt")
                    sudokuArray.forEachIndexed { i, ints ->
                        ints.forEachIndexed { j, number ->
                            array[i][j].value = number.toString()
                        }
                    }
                }) {
                    Text(text = "Загрузить из файла")
                }

                Button(onClick = {
                    array.forEach { list ->
                        list.forEach {
                            it.value = "0"
                        }
                    }
                }) {
                    Text(text = "Очистить")
                }
            }
        }
    }
}

private fun readFile(fileName: String): Array<IntArray> {
    val bufferedReader = File(fileName).bufferedReader()
    return bufferedReader.readLines().map { line ->
        line.split(" ").map { char -> char.toInt() }.toIntArray()
    }.toTypedArray()
}

@Composable
private fun createTextArray(): List<List<MutableState<String>>> {
    val array = mutableListOf<List<MutableState<String>>>()
    repeat(times = SIZE) {
        val list = mutableListOf<MutableState<String>>()
        repeat(times = SIZE) {
            val state = remember { mutableStateOf("0") }
            list.add(state)
        }
        array.add(list)
    }
    return array
}

@Composable
private fun SudokuTextField(text: String, modifier: Modifier, setText: (String) -> Unit) {

    TextField(
        modifier = modifier.size(size = 64.dp),
        value = text,
        onValueChange = setText,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            textColor = Color.Black,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,

            ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            imeAction = ImeAction.Next
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        ),
        shape = RectangleShape
    )
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Лосев Дмитрий А-13м-21",
        state = WindowState(width = 800.dp, height = 800.dp)
    ) {
        App()
    }
}
