
package summary

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HabitCheck(habit: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val circlePositions = listOf(
            Offset(100f, 50f),
            Offset(200f, 50f),
            Offset(300f, 50f),
            Offset(50f, 175f),
            Offset(150f, 175f),
            Offset(250f, 175f),
            Offset(350f, 175f)
        )
        val Weekday = listOf(
            "Mon",
            "Tue",
            "Wed",
            "Thu",
            "Fri",
            "Sat",
            "Sun"
        )
        val complete = listOf(
            true,
            true,
            true,
            true,
            false,
            false,
            false
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            drawRect(
                color = Color.Transparent,
                size = size
            )
            circlePositions.forEachIndexed { index, position ->
                if (complete[index]) {
                    drawCircle(
                        color = Color.Blue,
                        radius = 30f,
                        center = position
                    )
                } else {
                    drawCircle(
                        color = Color.Gray,
                        radius = 30f,
                        center = position
                    )
                }

            }

        }
        circlePositions.forEachIndexed { index, position ->
            Text(
                text = Weekday[index],
                modifier = Modifier.offset(x = position.x.dp-2.dp, y = position.y.dp+3.dp),
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}
