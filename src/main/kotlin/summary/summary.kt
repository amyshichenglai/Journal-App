package summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontFamily
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.DateFormatSymbols

val currentDate = LocalDate.now()
val currentDateTime = LocalDateTime.now()

fun getMonthName(monthNumber: Int): String {
    val symbols = DateFormatSymbols()
    val monthNames = symbols.months
    return monthNames[monthNumber]
}

fun formatTimeAs24HourClock(dateTime: LocalDateTime): String {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    return dateTime.format(timeFormatter)
}


@Composable
fun Summary() {
    var currentDate by remember { mutableStateOf(LocalDate.now()) }
    var Date1 by remember { mutableStateOf(currentDate.format(DateTimeFormatter.ofPattern("dd"))) }
    var Date2 by remember { mutableStateOf(currentDate.plusDays(7).format(DateTimeFormatter.ofPattern("dd"))) }
    var monthNumber by remember { mutableStateOf(currentDate.format(DateTimeFormatter.ofPattern("MM")).toInt() - 1) }
    var currMonth by remember { mutableStateOf(getMonthName(monthNumber)) }
    var monthNumber2 by remember { mutableStateOf(currentDate.plusDays(7).format(DateTimeFormatter.ofPattern("MM")).toInt() - 1) }
    var currMonth2 by remember { mutableStateOf(getMonthName(monthNumber2)) }
    var currYear by remember { mutableStateOf(currentDate.format(DateTimeFormatter.ofPattern("yyyy"))) }

    LaunchedEffect(Unit) {
        while (true) {
            currentDate = LocalDate.now()
            Date1 = currentDate.format(DateTimeFormatter.ofPattern("dd"))
            Date2 = currentDate.plusDays(7).format(DateTimeFormatter.ofPattern("dd"))
            monthNumber = currentDate.format(DateTimeFormatter.ofPattern("MM")).toInt() - 1
            currMonth = getMonthName(monthNumber)
            currYear = currentDate.format(DateTimeFormatter.ofPattern("yyyy"))
            delay(1000) // Update every second or as needed
        }
    }

    var progress by remember { mutableStateOf(0.1f) }
    var selectedSection by remember { mutableStateOf("Weekly") }
    var habit by remember { mutableStateOf("All") }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            ReportTypeSelection(selectedSection) { newSection ->
                selectedSection = newSection
            }
        }

        item {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                when (selectedSection) {
                    "Weekly" -> Text(
                        text = "Week of " + currMonth + " " + Date1 + " - " + currMonth2 + " " + Date2,
                        fontFamily = FontFamily.Cursive,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    "Monthly" -> Text(
                        text = currMonth,
                        fontFamily = FontFamily.Cursive,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    "Annual" -> Text(
                        text = currYear,
                        fontFamily = FontFamily.Cursive,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        item {
            TaskProgress(
                progress = progress
            )
        }

        item {
            when (selectedSection) {
                "Weekly" -> LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    item {
                        Chart(

                            data = mapOf(

                                Pair("Mon", 0.1f),
                                Pair("Tue", 0.2f),
                                Pair("Wed", 0.3f),
                                Pair("Thu", 0.4f),
                                Pair("Fri", 0.5f),
                                Pair("Sat", 0.6f),
                                Pair("Sun", 0.7f),


                                ), barwidth = 30.dp, graphWidth = 530.dp, max_value = 1
                        )
                    }
                    item {
                        Column {
                            HabitSelection(habit) { newSection ->
                                habit = newSection
                            }
                            HabitCheck(habit)
                        }

                    }


                }


                "Monthly" -> Chart(

                    data = mapOf(
                        Pair("Week1", 0.1f),
                        Pair("Week2", 0.2f),
                        Pair("Week3", 0.3f),
                        Pair("Week4", 0.4f),
                        ), barwidth = 50.dp, graphWidth = 530.dp, max_value = 1
                )

                "Annual" -> Chart(

                    data = mapOf(

                        Pair("Jan", 0.1f),
                        Pair("Feb", 0.2f),
                        Pair("Mar", 0.3f),
                        Pair("Apr", 0.4f),
                        Pair("May", 0.5f),
                        Pair("Jun", 0.6f),
                        Pair("Jul", 0.7f),
                        Pair("Aug", 0.7f),
                        Pair("Sep", 0.7f),
                        Pair("Oct", 0.7f),
                        Pair("Nov", 0.7f),
                        Pair("Dec", 0.7f),

                        ), barwidth = 30.dp, graphWidth = 900.dp, max_value = 1
                )
            }
        }

        item {
            var habitscompleted = listOf("Habit1", "Habit2", "Habit3")
            Column {
                Text(
                    text = "Achievements",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                habitscompleted.forEach { habit ->
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .height(40.dp)
                            .width(200.dp)
                            .background(MaterialTheme.colorScheme.tertiary),
                        contentAlignment = Alignment.Center,
                        content = {
                            Text(
                                text = "$habit Completed",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        }
                    )
                }
            }

        }


    }
}

//barchart
//https://github.com/developerchunk/BarGraph-JetpackCompose/tree/main/app/src/main/java/com/example/customchar
