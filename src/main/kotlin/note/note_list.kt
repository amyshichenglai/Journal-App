package note

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


object FoldersTable : Table() {
    val id = integer("id")
    val name = varchar("name", 255)
    var numberOfItem = integer("num")
    val parentFolderId = integer("parent_id").references(FoldersTable.id).nullable()
}

object FilesTable : Table() {
    val id = integer("id")
    val name = varchar("name", 255)
    val content = text("content")
    val folderId = integer("folder_id").references(FoldersTable.id)
}

@Composable
fun NoteList() {
    val selectedNoteIndex = remember { mutableStateOf<Int?>(null) }
    val currentFolderID = remember { mutableStateOf<Int?>(0) }
    var numItemInFolder = remember { mutableStateOf<Int?>(0) }
    Box(Modifier.fillMaxSize()) {
        LazyColumn() {
            numItemInFolder.value?.let {
                items(it) {
                    val backgroundColor = if (it == selectedNoteIndex.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onPrimary
                    val fontWeight = if (it == selectedNoteIndex.value) FontWeight.Bold else FontWeight.Normal

                    ElevatedCard (
                        // shadow
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 3.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = backgroundColor
                        ),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Row (modifier = Modifier
                            .clickable { selectedNoteIndex.value = it }
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                        ) {
                            Icon(
                                Icons.Filled.Edit, // icon image
                                contentDescription = "A Pen",
                                modifier = Modifier
                                    .clickable {  }
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 10.dp)
                            )
                            Text(
                                text = "Note ${it+1}",
                                fontWeight = fontWeight,
                                modifier = Modifier
                                    .padding(16.dp),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        Row (modifier = Modifier.align(Alignment.BottomCenter)) {
            // add file button
            FloatingActionButton(
                modifier = Modifier
                    .padding(10.dp)
                // .border(2.dp, MaterialTheme.colorScheme.tertiary)
                ,
                onClick = {println("+ pressed")},
                containerColor = MaterialTheme.colorScheme.tertiary,
                elevation =  FloatingActionButtonDefaults.elevation(
                    defaultElevation = 10.dp
                )
            ) {
                Icon(Icons.Filled.Edit, "Localized description")
            }

            // add folder button
            FloatingActionButton(
                modifier = Modifier
                    .padding(10.dp)
                // .border(2.dp, MaterialTheme.colorScheme.tertiary)
                ,
                onClick = {println("+ pressed")},
                containerColor = MaterialTheme.colorScheme.tertiary,
                elevation =  FloatingActionButtonDefaults.elevation(
                    defaultElevation = 10.dp
                )
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        }


    }
}
