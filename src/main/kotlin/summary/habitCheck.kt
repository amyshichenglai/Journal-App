
package summary

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.EmptyBuildDrawCacheParams.size
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Shape
//import androidx.compose.material3.md.sys.shape.corner.full.Circular
import androidx.compose.ui.draw.drawBehind
import java.awt.Color.red


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
        val radius = 60f
        circlePositions.forEachIndexed {index, position ->
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .offset(position.x.dp, y=position.y.dp)
                    .drawBehind {
                        if (complete[index]) {
                            drawCircle(
                                color = Color(0xFF476810), //primary
                                radius = radius
                            )
                        } else {
                            drawCircle(
                                color = Color(0xFFC5C8B9),
                                radius = radius
                            )
                        }
                    },
                text = Weekday[index],
                color =
                    if (complete[index]) {
                        Color(0xFFFFFFFF)//onPrimary
                    } else {
                        Color.Black
                }
            )
        }
    }
}
