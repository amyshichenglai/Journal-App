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
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.random.Random

object TodoTable : Table() {
    val id = integer("id")
    val primaryTask = varchar("primaryTask", 255)
    val secondaryTask = varchar("secondaryTask", 255)
    val priority = integer("priority")
    val completed = bool("completed")
}

data class TodoItem(
    val id: Int,
    val primaryTask: String,
    val secondaryTask: String,
    val priority: Int,
    var completed: Boolean
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoList() {
    Database.connect("jdbc:sqlite:chinook.db")
    val (selectedSection, setSelectedSection) = remember { mutableStateOf("Section 1") }
    Column(
        modifier = Modifier.fillMaxHeight().padding(top = 24.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().weight(0.1f)  // 10% of parent's height
            // other modifiers, content, etc.
        ) {
            Row() {
                val commonButtonModifier = Modifier.weight(1f).padding(14.dp).size(width = 150.dp, height = 1000.dp)
                OutlinedButton(
                    onClick = { setSelectedSection("Home") }, modifier = commonButtonModifier
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
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
            modifier = Modifier.fillMaxWidth().weight(0.9f)  // 90% of parent's height
            // other modifiers, content, etc.
        ) {
            val triggerRecomposition = remember { mutableStateOf(false) }
            LazyColumn() {
                val todoListFromDb: MutableList<TodoItem> = mutableListOf()
                transaction {
                    TodoTable.selectAll().forEach {
                        todoListFromDb.add(
                            TodoItem(
                                it[TodoTable.id],
                                it[TodoTable.primaryTask],
                                it[TodoTable.secondaryTask],
                                it[TodoTable.priority],
                                it[TodoTable.completed]
                            )
                        )
                    }
                }

                todoListFromDb.forEachIndexed { index, todoItem ->
                    item {
                        ListItem(
                            headlineContent = { Text(todoItem.primaryTask) },
                            supportingContent = { Text(todoItem.secondaryTask) },
                            trailingContent = { Text("Priority ${todoItem.priority}") },
                            leadingContent = {
                                Checkbox(checked = todoItem.completed,
                                    onCheckedChange = { isChecked ->
                                        // Update local state
                                        todoItem.completed = isChecked
                                        // Update database
                                        transaction {
                                            TodoTable.update({ TodoTable.id eq todoItem.id }) {
                                                it[completed] = isChecked
                                            }
                                        }
                                    })
                            }
                        )
                        Divider()
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(), // This will make the Box take up the entire available space
                contentAlignment = Alignment.BottomEnd // This will align its children to the bottom right corner
            ) {
                ExtendedFloatingActionButton(modifier = Modifier.padding(bottom = 16.dp, end = 16.dp),
                    onClick = { /* do something */ }) {
                    Text(text = "Create New")
                }
            }
        }
    }
}


