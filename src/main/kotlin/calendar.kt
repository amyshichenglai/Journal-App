import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun SimpleCalendar(month: Int, year: Int) {
    // Get the day of the week for the first day of the month
    val firstDayOfMonth = LocalDate.of(year, month, 1).dayOfWeek.value % 7
    val daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth()

    // List of day names
    val dayNames = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    // Month names list
    val monthNames = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val monthName = monthNames[month - 1]  // Arrays are 0-indexed, so subtract 1 from the month number

    // Use a vertical Column to hold the month and year, day names, and the calendar dates
    Column {
        // Month and Year
        Text(text = "$monthName $year", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp, top = 8.dp))

        Spacer(modifier = Modifier.height(8.dp))
        // Day Names Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            dayNames.forEach { dayName ->
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = dayName,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        //Spacer(modifier = Modifier.height(8.dp))

        // Calendar Dates
        var currentDate = 1

        // Create rows for weeks
        for (i in 0..5) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Create individual date cells
                for (j in 0..6) {
                    Box(
                        contentAlignment = Alignment.TopCenter,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    ) {
                        // Only show date if within the month and after the starting weekday
                        if (currentDate <= daysInMonth && (i > 0 || j >= firstDayOfMonth)) {
                            Text(text = currentDate.toString())
                            currentDate++
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Calendar() {
    SimpleCalendar(10,2023)
}