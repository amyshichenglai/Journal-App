package note

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

@Composable
fun myButton(
    isSelected: Boolean,
    pickCoor: Color,


) {}

@Composable
fun DocumentToolbar(
    onPlain: () -> Unit,
    onBold: () -> Unit,
    onItalic: () -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TextButton(onClick = onPlain, modifier = Modifier.focusable(false)) {
            Text("Plain")
        }
        TextButton(onClick = onBold, modifier = Modifier.focusable(false)) {
            Text("Bold")
        }
        TextButton(onClick = onItalic, modifier = Modifier.focusable(false)) {
            Text("Italic")
        }
        TextButton(onClick = onUndo, modifier = Modifier.focusable(false)) {
            Text("Undo")
        }
        TextButton(onClick = onRedo, modifier = Modifier.focusable(false)) {
            Text("Redo")
        }
    }
}

@Composable
fun EditorInterface() {

    val state = rememberRichTextState()
    val focusRequester = remember { FocusRequester() }
    Box(
        modifier = Modifier.fillMaxSize().padding(14.dp),
        // contentAlignment = Alignment.TopStart
    ) {
        DocumentToolbar(
            onPlain = {
                state.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Normal))
                state.toggleSpanStyle(SpanStyle(fontStyle = FontStyle.Normal))
            },
            onBold = {
                state.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
                state.toggleSpanStyle(SpanStyle(fontStyle = FontStyle.Normal))
            },
            onItalic = {
                state.toggleSpanStyle(SpanStyle(fontStyle = FontStyle.Italic))
            },
            onUndo = {
                // Implement undo functionality
            },
            onRedo = {
                // Implement redo functionality
            }
        )

        // Toggle a span style.
        RichTextEditor(
            state = state,
            modifier = Modifier.fillMaxSize().padding(top = 55.dp),
        )
    }
}

@Composable
fun NoteList() {
    val selectedNoteIndex = remember { mutableStateOf<Int?>(null) }

    LazyColumn() {
        items(20) {
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

@Composable
fun NotesEditor() {
    Row(
        modifier = Modifier.fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.15f)  // 15% of parent's height
                .padding(top = 70.dp)
            // other modifiers, content, etc.
        ) {
            NoteList()
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.85f)  // 85% of parent's height
            // other modifiers, content, etc.
        ) {
            EditorInterface()
        }

    }
}

