import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application




// Sample Composable functions for each section
@Composable
fun Calendar() {
    Text("Calendar")
}

@Composable
fun BoxItem(color: Color, text: String) {
    Box(modifier = Modifier.size(200.dp, 200.dp).background(color)) {
        Text(text)
    }
}
@Composable
fun MagicHome() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(4) { index ->
            when (index) {
                0 -> BoxItem(Color.Red, "Summary section")
                1 -> BoxItem(Color.Yellow, "Calendar section")
                2 -> BoxItem(Color.Blue, "Todo section")
                3 -> BoxItem(Color.Green, "Notes section")
            }
        }
    }
}

@Composable
fun Notes() {
    Text("This is the Notes content")
}

@Composable
fun AppLayout() {
    val (selectedSection, setSelectedSection) = remember { mutableStateOf("Section 1") }
    Row() {
        Column(
//            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { setSelectedSection("Home") }, modifier = Modifier.weight(1f).padding(16.dp)) {
                Text("Home")
            }
            Button(onClick = { setSelectedSection("Calendar") }, modifier = Modifier.weight(1f).padding(16.dp)) {
                Text("Calendar")
            }
            Button(onClick = { setSelectedSection("Summary") }, modifier = Modifier.weight(1f).padding(16.dp)) {
                Text("Summary")
            }
            Button(onClick = { setSelectedSection("To-Do-List") }, modifier = Modifier.weight(1f).padding(16.dp)) {
                Text("To-Do-List")
            }
            Button(onClick = { setSelectedSection("Notes") }, modifier = Modifier.weight(1f).padding(16.dp)) {
                Text("Notes")
            }
        }
        Box(
            modifier = Modifier.weight(3f).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            when (selectedSection) {
                "Home" -> MagicHome()
                "Calendar" -> Calendar()
                "Summary" -> Summary()
                "To-Do-List" -> ToDoList()
                "Notes" -> Notes()
                else -> MagicHome()
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            AppLayout()
        }
    }
}

