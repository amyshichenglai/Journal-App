import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip

@Composable
fun homeCalendar() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
//        .aspectRatio(3f)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(250.dp, 400.dp) // Adjust these values as per your desired size
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFede6fa)), // Optional: If you want a background color
            contentAlignment = Alignment.TopEnd
        ) {
            DailyCalendar(date = 14, month = 10, year = 2023)
        }
    }

}