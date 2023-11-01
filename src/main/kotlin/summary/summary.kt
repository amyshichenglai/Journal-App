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
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
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

fun getMondayOfCurrentWeek(date: LocalDate): String {
    val dayOfWeek = date.dayOfWeek.value
    val monday = date.minusDays((dayOfWeek - 1).toLong())
    return monday.format(DateTimeFormatter.ofPattern("dd"))
}

fun getSundayOfCurrentWeek(date: LocalDate): String {
    val dayOfWeek = date.dayOfWeek.value
    val sunday = date.plusDays((7 - dayOfWeek).toLong())
    return sunday.format(DateTimeFormatter.ofPattern("dd"))
}

@Composable
fun Summary() {
    Database.connect("jdbc:sqlite:chinook.db")
    val todoListFromDb: MutableList<TodoItem> = mutableListOf()

    transaction {
        TodoTable.selectAll().forEach {
            todoListFromDb.add(
                TodoItem(
                    it[TodoTable.id],
                    it[TodoTable.primaryTask],
                    it[TodoTable.secondaryTask],
                    it[TodoTable.priority],
                    it[TodoTable.completed],
                    it[TodoTable.section],
                    it[TodoTable.datetime],
                    it[TodoTable.duration]
                )
            )
        }
    }
    var completed = todoListFromDb.filter { it.completed == true }
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")

    var currentDate by remember { mutableStateOf(LocalDate.now()) }
    var Date1 by remember { mutableStateOf(getMondayOfCurrentWeek(currentDate)) }
    var Date2 by remember { mutableStateOf(getSundayOfCurrentWeek(currentDate)) }
    var monthNumber by remember { mutableStateOf(currentDate.format(DateTimeFormatter.ofPattern("MM")).toInt() - 1) }
    var currMonth by remember { mutableStateOf(getMonthName(monthNumber)) }
    var monthNumber2 by remember { mutableStateOf(currentDate.plusDays(7).format(DateTimeFormatter.ofPattern("MM")).toInt() - 1) }
    var currMonth2 by remember { mutableStateOf(getMonthName(monthNumber2)) }
    var currYear by remember { mutableStateOf(currentDate.format(DateTimeFormatter.ofPattern("yyyy"))) }

    LaunchedEffect(Unit) {
        while (true) {
            currentDate = LocalDate.now()
            Date1 = getMondayOfCurrentWeek(currentDate)
            Date2 = getSundayOfCurrentWeek(currentDate)
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
                        var monDuration = todoListFromDb.filter{ LocalDate.parse(it.datetime, formatter).dayOfWeek.toString() == "MONDAY"}.map { it.duration }.sum().toFloat()
                        var tueDuration = todoListFromDb.filter{ LocalDate.parse(it.datetime, formatter).dayOfWeek.toString() == "TUESDAY"}.map { it.duration }.sum().toFloat()
                        var wedDuration = todoListFromDb.filter{ LocalDate.parse(it.datetime, formatter).dayOfWeek.toString() == "WEDNESDAY"}.map { it.duration }.sum().toFloat()
                        var thuDuration = todoListFromDb.filter{ LocalDate.parse(it.datetime, formatter).dayOfWeek.toString() == "THURSDAY"}.map { it.duration }.sum().toFloat()
                        var friDuration = todoListFromDb.filter{ LocalDate.parse(it.datetime, formatter).dayOfWeek.toString() == "FRIDAY"}.map { it.duration }.sum().toFloat()
                        var satDuration = todoListFromDb.filter{ LocalDate.parse(it.datetime, formatter).dayOfWeek.toString() == "SATURDAY"}.map { it.duration }.sum().toFloat()
                        var sunDuration = todoListFromDb.filter{ LocalDate.parse(it.datetime, formatter).dayOfWeek.toString() == "SUNDAY"}.map { it.duration }.sum().toFloat()

                        Chart(
                            data = mapOf(
                                Pair("Mon", monDuration),
                                Pair("Tue", tueDuration),
                                Pair("Wed", wedDuration),
                                Pair("Thu", thuDuration),
                                Pair("Fri", friDuration),
                                Pair("Sat", satDuration),
                                Pair("Sun", sunDuration),
                                ), barwidth = 30.dp, graphWidth = 530.dp,
                            max_value = listOf(monDuration, tueDuration, wedDuration, thuDuration, friDuration, satDuration, sunDuration).max()
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
                        ), barwidth = 50.dp, graphWidth = 530.dp, max_value = 1.0f
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

                        ), barwidth = 30.dp, graphWidth = 900.dp, max_value = 1.0f
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
