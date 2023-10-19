package note

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import androidx.compose.runtime.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.input.pointer.pointerInput
import com.mohamedrejeb.richeditor.model.RichTextStyle


@Composable
fun myButton(
    isChoosed: Boolean,
    choosedColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    unchoosedColor: Color = Color.Transparent,
    changeColor: (Boolean) -> Unit,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(size = 5.dp))
            .clickable {
                onClick()
                changeColor(!isChoosed)
            }
            .background(
                if (isChoosed) { choosedColor } else { unchoosedColor }
            )
    ) {
        content()
    }
}

@Composable
fun DocumentToolbar(
    onHead: () -> Unit,
    onBold: () -> Unit,
    onItalic: () -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit
) {
    var headChoosed by remember { mutableStateOf(false) }
    var boldChoosed by remember { mutableStateOf(false) }
    var italicChoosed by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp).focusable(false),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
//        myButton(
//            isChoosed = headChoosed,
//            changeColor = {headChoosed = it},
//            onClick = onHead
//        ) {
//            Text(
//                text = "Head",
//                modifier = Modifier.padding(3.dp),
//                color = MaterialTheme.colorScheme.secondary
//            )
//        }
        TextButton(onClick = onHead, modifier = Modifier.focusable(false)) {
            Text("Head")
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
            onHead = {
                state.toggleSpanStyle(SpanStyle(fontSize = 23.sp))
            },
            onBold = {
                state.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
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
