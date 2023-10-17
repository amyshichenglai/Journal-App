import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import java.io.FileInputStream
import java.io.InputStream
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import ui.theme.AppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import summary.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.xml.sax.InputSource
import java.io.File
import java.io.IOException
import androidx.compose.ui.window.*
import java.awt.Dimension
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.material3.*
import androidx.compose.*
import androidx.compose.foundation.Canvas
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.graphics.withSave
import androidx.compose.ui.res.*
//import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoList() {
    val (selectedSection, setSelectedSection) = remember { mutableStateOf("Section 1") }
    Column(
        modifier = Modifier.fillMaxHeight()
            .padding(top = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)  // 10% of parent's height
            // other modifiers, content, etc.
        ) {
            Row() {
                val commonButtonModifier = Modifier
                    .weight(1f)
                    .padding(14.dp)
                    .size(width = 150.dp, height = 1000.dp)
                OutlinedButton(
                    onClick = { setSelectedSection("Home") }, modifier = commonButtonModifier
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
//                            Icon(
//                                painter = painterResource("home.svg"),
//                                contentDescription = null
//                            )

                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Work")
                    }
                }
                OutlinedButton(
                    onClick = { setSelectedSection("Calendar") }, modifier = commonButtonModifier
                ) {
                    Text(
                        text = "Study"
                    )
                }
                OutlinedButton(
                    onClick = { setSelectedSection("Summary") }, modifier = commonButtonModifier
                ) {
                    Text("Hobby")
                }
                OutlinedButton(
                    onClick = { setSelectedSection("To-Do-List") }, modifier = commonButtonModifier
                ) {
                    Text("Life")
                }


            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.9f)  // 90% of parent's height
            // other modifiers, content, etc.
        ) {
            Column() {

                for (i in 0 until 5) {
                    val checkedState = remember { mutableStateOf(false) }
                    ListItem(headlineContent = { Text("One line list item with 24x24 icon") },
                        supportingContent = { Text("Secondary text") },
                        trailingContent = { Text("Priority 3") },
                        leadingContent = {
                            Checkbox(
                                checked = checkedState.value,
                                onCheckedChange = { checkedState.value = it }
                            )
                        }
                    )
                    Divider()
                }

            }

        }
    }
}


