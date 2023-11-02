package note

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.richeditor.model.RichTextState

@Composable
fun functionButton(
    onClick: () -> Unit,
    text: String,
    isSelected: Boolean = false,
) {
    Button(
        onClick = onClick,
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 3.dp
//        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.tertiary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
            contentColor = if (isSelected) {
                MaterialTheme.colorScheme.onTertiary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
        ),
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .focusProperties { canFocus = false }
    ) {
        Text(text)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun controlBar(
    state: RichTextState,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        functionButton(
            onClick = {
                state.addParagraphStyle(
                    ParagraphStyle(
                        textAlign = TextAlign.Left,
                    )
                )
            },
            isSelected = state.currentParagraphStyle.textAlign == TextAlign.Left,
            text = "Left"
        )

        functionButton(
            onClick = {
                state.addParagraphStyle(
                    ParagraphStyle(
                        textAlign = TextAlign.Center
                    )
                )
            },
            isSelected = state.currentParagraphStyle.textAlign == TextAlign.Center,
            text = "Center"
        )

        functionButton(
            onClick = {
                state.addParagraphStyle(
                    ParagraphStyle(
                        textAlign = TextAlign.Right
                    )
                )
            },
            isSelected = state.currentParagraphStyle.textAlign == TextAlign.Right,
            text = "Right"
        )

        functionButton(
            onClick = {
                state.toggleSpanStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            isSelected = state.currentSpanStyle.fontWeight == FontWeight.Bold,
            text = "Bold"
        )

        functionButton(
            onClick = {
                state.toggleSpanStyle(
                    SpanStyle(
                        fontStyle = FontStyle.Italic
                    )
                )
            },
            isSelected = state.currentSpanStyle.fontStyle == FontStyle.Italic,
            text = "Italic"
        )

        functionButton(
            onClick = {
                state.toggleSpanStyle(
                    SpanStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
            },
            isSelected = state.currentSpanStyle.textDecoration?.contains(TextDecoration.Underline) == true,
            text = "Underline"
        )

        functionButton(
            onClick = {
                state.toggleSpanStyle(
                    SpanStyle(
                        textDecoration = TextDecoration.LineThrough
                    )
                )
            },
            isSelected = state.currentSpanStyle.textDecoration?.contains(TextDecoration.LineThrough) == true,
            text = "Line Through"
        )

        functionButton(
            onClick = {
                state.toggleSpanStyle(
                    SpanStyle(
                        fontSize = 28.sp
                    )
                )
            },
            isSelected = state.currentSpanStyle.fontSize == 28.sp,
            text = "Title"
        )

        functionButton(
            onClick = {
                state.toggleSpanStyle(
                    SpanStyle(
                        color = Color.Red
                    )
                )
            },
            isSelected = state.currentSpanStyle.color == Color.Red,
            text = "Hight Line Red"
        )

        functionButton(
            onClick = {
                state.toggleSpanStyle(
                    SpanStyle(
                        background = Color.Yellow
                    )
                )
            },
            isSelected = state.currentSpanStyle.background == Color.Yellow,
            text = "Hight Line Yellow",
        )

        functionButton(
            onClick = {
                state.toggleUnorderedList()
            },
            isSelected = state.isUnorderedList,
            text = "List Point",
        )

        functionButton(
            onClick = {
                state.toggleOrderedList()
            },
            isSelected = state.isOrderedList,
            text = "List Num",
        )

        functionButton(
            onClick = {
                val html = "<p><b>Compose Rich Editor</b></p>"
                state.setHtml(html)
            },
            text = "load",
        )
    }
}