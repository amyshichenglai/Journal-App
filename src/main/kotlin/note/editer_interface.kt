package note

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor

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