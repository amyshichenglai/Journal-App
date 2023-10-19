import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalDate
;import androidx.compose.material.Button
import androidx.compose.runtime.*
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme


@Composable
fun MonthlyCalendar(month: Int, year: Int) {
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
fun DailyCalendar(date: Int, month: Int, year: Int) {
    val selectedDate = LocalDate.of(year, month, date)
    val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Display the date
        Text(text = dateFormatter.format(selectedDate), fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Display the hours of the day
        (0..23).forEach { hour ->
            Text(
                text = "${if (hour < 10) "0$hour" else hour}:00",
                fontSize = 18.sp,
               // modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun Calendar() {
    var month by remember { mutableStateOf(10) }
    var year by remember { mutableStateOf(2023) }
    var date by remember { mutableStateOf(17)}
    var mode by remember {mutableStateOf(0)}
    val selectedDate = LocalDate.of(year, month, date)
    if (mode == 0) {
        MonthlyCalendar(month,year)
    } else {
        DailyCalendar(date,month,year)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Row {
            FilledTonalButton(
                onClick = {
                    mode = 1
                },
                modifier = Modifier.size(105.dp, 32.dp) // Adjust the width and height as needed
            ) {
                Text("Daily")
            }
            FilledTonalButton(
                onClick = {
                    mode = 0
                },
                modifier = Modifier.size(105.dp, 32.dp) // Adjust the width and height as needed
            ) {
                Text("Monthly")
            }

        }

    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopEnd
    ) {
        Row {
            FilledTonalButton(
                onClick = {
                    if (mode == 0) {
                        if (month > 1) month-- else {
                            month = 12
                            year--
                        }
                    } else {
                        if (date > 1) {
                            date--
                        } else {
                            if (month > 1) {
                                month--
                                date = LocalDate.of(year, month, 1).lengthOfMonth()  // Set date to the last day of the previous month
                            } else {
                                month = 12
                                year--
                                date = 31
                            }
                        }
                    }

                },
                modifier = Modifier.size(70.dp, 30.dp) // Adjust the width and height as needed
            ) {
                Text("<")
            }
            FilledTonalButton(
                onClick = {
                    if (mode == 0) {
                        if (month < 12) month++ else {
                            month = 1
                            year++
                        }
                    } else {
                        if (date < selectedDate.lengthOfMonth()) {
                            date++
                        } else {
                            if (month < 12) {
                                month++
                                date = 1
                            } else {
                                month = 1
                                date = 1
                                year++
                            }
                        }
                    }
                },
                modifier = Modifier.size(70.dp, 30.dp) // Adjust the width and height as needed
            ) {
                Text(">")
            }

        }
    }
}