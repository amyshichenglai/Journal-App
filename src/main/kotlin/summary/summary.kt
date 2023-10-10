package summary

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

var Date1 = "10"
var Date2 = "17"
var currMonth = "October"
var currYear = "2023"

@Composable
fun Summary() {
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
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                when (selectedSection) {
                    "Weekly" -> Text(
                        text = "Week of " + currMonth + " " + Date1 + " - " + currMonth + " " + Date2,
                        fontFamily = FontFamily.Cursive,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )

                    "Monthly" -> Text(
                        text = currMonth,
                        fontFamily = FontFamily.Cursive,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )

                    "Annual" -> Text(
                        text = currYear,
                        fontFamily = FontFamily.Cursive,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
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
                        item{
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
                        )}
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

                            ), barwidth = 30.dp, graphWidth = 900.dp,max_value = 1
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
                            .background(Color.Blue),
                        contentAlignment = Alignment.Center,
                        content = {
                            Text(
                                text = "$habit Completed",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
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
