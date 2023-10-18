package note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text
import androidx.compose.ui.text.font.FontFamily
import java.time.format.TextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.foundation.text.selection.*

@Composable
fun DocumentToolbar(
    onOpen: () -> Unit,
    onSave: () -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onOpen) {
            Text("Open")
        }
        Button(onClick = onSave) {
            Text("Save")
        }
         // Adds some space between buttons
        Button(onClick = onUndo) {
            Text("Undo")
        }
        Button(onClick = onRedo) {
            Text("Redo")
        }
    }
}

@Composable
fun NotesEditor() {
    var text by remember { mutableStateOf("") }


    Box(
        modifier = Modifier.fillMaxSize().padding(14.dp),
        // contentAlignment = Alignment.TopStart
    ) {
        DocumentToolbar(
            onOpen = {
                     //
            },
            onSave = {
            },
            onUndo = {
                // Implement undo functionality
            },
            onRedo = {
                // Implement redo functionality
            }
        )

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(fontSize = 18.sp),
            maxLines = Int.MAX_VALUE, // Allow multiple lines
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxSize().padding(top = 55.dp),
            label = { Text("Label") }

        )
    }
}