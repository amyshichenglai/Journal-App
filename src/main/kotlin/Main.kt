import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import ui.theme.AppTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import summary.*
import androidx.compose.ui.window.*
import java.awt.Dimension
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.material3.*
import androidx.compose.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.sp

// Sample Composable functions for each section

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
                0 -> HomeSummary()
//                1 -> BoxItem(Color.Yellow, "Calendar section")
//                2 -> BoxItem(Color.Blue, "Todo section")
//                3 -> BoxItem(Color.Green, "Notes section")
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
            //verticalArrangement = Arrangement.SpaceBetween

            verticalArrangement = Arrangement.SpaceBetween, // Controls vertical arrangement
            horizontalAlignment = Alignment.CenterHorizontally, // Controls horizontal alignment
            modifier = Modifier.fillMaxHeight()

        ) {
            val commonButtonModifier = Modifier
                .weight(1f)
                .padding(14.dp)
                .size(width = 150.dp, height = 1000.dp)
            Button(
                onClick = { setSelectedSection("Home") }, modifier = commonButtonModifier
            ) {
                Text("Home")
            }
            FilledTonalButton(
                onClick = { setSelectedSection("Calendar") }, modifier = commonButtonModifier
            ) {
                Text(
                    text = "Calendar"
                )
            }
            FilledTonalButton(
                onClick = { setSelectedSection("Summary") }, modifier = commonButtonModifier
            ) {
                Text("Summary")
            }
            FilledTonalButton(
                onClick = { setSelectedSection("To-Do-List") }, modifier = commonButtonModifier
            ) {
                Text("To-Do-List")
            }
            FilledTonalButton(
                onClick = { setSelectedSection("Notes") }, modifier = commonButtonModifier
            ) {
                Text("Notes")
            }

        }
        Box(
            modifier = Modifier.weight(3f).fillMaxWidth(), contentAlignment = Alignment.Center
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
    val window = Window(
        onCloseRequest = ::exitApplication, title = "Your App Title"
    ) {
        window.minimumSize = Dimension(1200, 700)
        AppTheme {
            AppLayout()
        }
    }
}

