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

import com.darkrockstudios.richtexteditor.model.RichTextValue
import com.darkrockstudios.richtexteditor.model.Style
import com.darkrockstudios.richtexteditor.ui.RichText
import com.darkrockstudios.richtexteditor.ui.RichTextEditor
import com.darkrockstudios.richtexteditor.ui.defaultRichTextFieldStyle
import com.darkrockstudios.richtexteditor.ui.defaultRichTextStyle

import androidx.compose.ui.unit.ExperimentalUnitApi
import com.darkrockstudios.richtexteditor.mappers.StyleMapper

import androidx.compose.runtime.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.input.pointer.pointerInput
import com.mohamedrejeb.richeditor.model.RichTextStyle


@Composable
fun myButton(
    isSelected: Boolean,
    pickCoor: Color,
) {}

@Composable
fun DocumentToolbar(
    onHead: () -> Unit,
    onBold: () -> Unit,
    onItalic: () -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
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






//@Composable
//fun myButton(
//    isSelected: Boolean,
//    pickCoor: Color,
//) {}
//
//@Composable
//fun DocumentToolbar(
//    onPlain: () -> Unit,
//    onBold: () -> Unit,
//    onItalic: () -> Unit,
//    onUndo: () -> Unit,
//    onRedo: () -> Unit
//) {
//    Row(
//        modifier = Modifier.fillMaxWidth().padding(8.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly
//    ) {
//        TextButton(onClick = onPlain, modifier = Modifier.focusable(false)) {
//            Text("Plain")
//        }
//        TextButton(onClick = onBold, modifier = Modifier.focusable(false)) {
//            Text("Bold")
//        }
//        TextButton(onClick = onItalic, modifier = Modifier.focusable(false)) {
//            Text("Italic")
//        }
//        TextButton(onClick = onUndo, modifier = Modifier.focusable(false)) {
//            Text("Undo")
//        }
//        TextButton(onClick = onRedo, modifier = Modifier.focusable(false)) {
//            Text("Redo")
//        }
//    }
//}
//
//private const val text = """Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nisi nisi, pellentesque ac ultricies sed, varius at nunc. Integer vitae semper justo. Ut non nunc vel dolor interdum consectetur. Nam ullamcorper molestie odio, vel aliquet est viverra at. Quisque lectus sapien, finibus nec tincidunt in, vulputate vitae neque. Suspendisse mollis elit velit, a pulvinar eros dictum a. Nunc in ornare metus, sed dapibus nibh. Pellentesque suscipit enim non ultricies auctor. In elit metus, vulputate at turpis ut, scelerisque dictum ipsum. Curabitur in nisl auctor mi lobortis tincidunt. In ultrices luctus bibendum. Proin volutpat, purus tempor iaculis pulvinar, leo erat malesuada mi, nec sollicitudin metus erat commodo velit. Sed in diam sed ante convallis pharetra sit amet eget diam.
//
//Integer massa mauris, commodo sit amet quam id, vulputate commodo ante. Morbi felis purus, aliquam sit amet mattis et, pulvinar eu augue. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus pretium quam sit amet nisl tempus pretium. Aliquam nibh ipsum, porta quis nulla iaculis, malesuada ullamcorper purus. Phasellus eu fringilla lorem. Praesent condimentum felis in dui dictum scelerisque. Maecenas quis dictum leo. Fusce at fringilla mauris. Vivamus vel metus eget dui condimentum efficitur a sed ante. Vestibulum finibus nisl tincidunt tempor semper. Etiam purus felis, tempus in ultricies sit amet, fermentum vitae lorem. Duis aliquet, ipsum et ornare pretium, dolor diam pellentesque sapien, a maximus velit ligula id lorem.
//
//Vestibulum vulputate ultricies vulputate. In ut hendrerit mauris. Maecenas sit amet dui justo. Nullam egestas eros ut justo rutrum egestas. Fusce ultrices lobortis ex, sed placerat tellus tincidunt at. Duis congue ornare suscipit. Mauris blandit dapibus fermentum. In id libero posuere, pharetra enim non, malesuada nisi. Donec ac eros sed elit congue auctor. Maecenas eros purus, rhoncus at dui accumsan, dapibus consequat magna. Nunc nec rhoncus mi. In placerat velit at velit tincidunt, sed feugiat enim malesuada. Phasellus libero arcu, fermentum congue libero sed, tincidunt porttitor mauris. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Morbi in magna ullamcorper, malesuada nibh nec, porta lectus.
//
//Nullam nec lorem elit. Sed quis elementum nibh. Nam congue mauris ac aliquet imperdiet. Donec vulputate orci et pretium viverra. Vivamus euismod auctor tristique. Sed velit est, ornare quis arcu quis, hendrerit auctor massa. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
//
//Phasellus varius at augue eu interdum. Integer dapibus egestas risus, vitae tempor lorem elementum in. Cras euismod tellus quis eleifend dictum. Aliquam quis pharetra tellus. Cras molestie auctor sollicitudin. Nam et fringilla lacus, mollis auctor massa. Quisque pretium aliquet augue ac rutrum. Donec at consectetur odio, at rutrum nisl.
//"""
//
//@Composable
//fun EditorInterface() {
//
//    var value by remember {
//        mutableStateOf(
//            RichTextValue.fromString(
//                text = text,
//                // Optional parameter; leave it blank if you want to use provided styles
//                // But if you want to customize the user experience you're free to do that
//                // by providing a custom StyleMapper
//                // styleMapper = CustomStyleMapper()
//            )
//        )
//    }
////    val state = rememberRichTextState()
//    val focusRequester = remember { FocusRequester() }
//    Column(
//        modifier = Modifier.fillMaxSize().padding(16.dp),
//        // contentAlignment = Alignment.TopStart
//    ) {
//        DocumentToolbar(
//            onPlain = {
//
//            },
//            onBold = {
//
//            },
//            onItalic = {
//
//            },
//            onUndo = {
//                // Implement undo functionality
//            },
//            onRedo = {
//                // Implement redo functionality
//            }
//        )
//
//        // make a box to contain the text Editor
//        val isClicked = remember { mutableStateOf(false) }
//        Box(
//            modifier = Modifier
//                //.clip(RoundedCornerShape(15.dp))
//                .background(MaterialTheme.colorScheme.surfaceVariant)
//                .fillMaxHeight()
//                .fillMaxWidth()
//                .onFocusEvent { isClicked.value = true }
//                .let {
//                    if (isClicked.value) it.border(3.dp, MaterialTheme.colorScheme.secondary) else it
//                }
//        ) {
//            RichTextEditor(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight()
//                    .padding(16.dp)
//                    .background(Color.Transparent),
//                value = value,
//                onValueChange = { value = it },
////                textFieldStyle = defaultRichTextFieldStyle().copy(
////                    textColor = MaterialTheme.colorScheme.onPrimaryContainer,
////                    placeholderColor = MaterialTheme.colorScheme.secondary,
////                    placeholder = "My rich text editor in action"
////                )
//            )
//        }
//
//    }
//}