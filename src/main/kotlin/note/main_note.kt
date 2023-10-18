package note

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*


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
         // Adds some space between buttons
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
    Column() {
        for (i in 0 until 5) {
            ListItem(
                tonalElevation = 34.dp,
                shadowElevation = 10.dp,
                headlineContent = { Text("Note${i}") },
                leadingContent = {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Localized description",
                    )
                }
            )
            Divider()
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
                .weight(0.15f)  // 10% of parent's height
                .padding(top = 70.dp)
            // other modifiers, content, etc.
        ) {
            NoteList()
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.85f)  // 90% of parent's height
            // other modifiers, content, etc.
        ) {
            EditorInterface()
        }

    }
}

