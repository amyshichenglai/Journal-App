package summary

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskProgress(
    progress: Float,
    modifier: Modifier = Modifier
        .padding(20.dp)
        .fillMaxSize()
) {
    Box(
        modifier = modifier.size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            backgroundColor = Color.White,
            progress = progress,
            modifier = Modifier.size(200.dp),
            strokeWidth = 12.dp
        )

            Text(
                text = "Task Progress",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )

    }
}
