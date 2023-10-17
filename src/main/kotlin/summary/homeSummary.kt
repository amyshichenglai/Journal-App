package summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.material.Button
import androidx.compose.runtime.*
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.background
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import java.time.LocalDate

//barchart
//https://github.com/developerchunk/BarGraph-JetpackCompose/tree/main/app/src/main/java/com/example/customchar

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
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun ButtonBox(date: String? = null, time: String? = null) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .height(50.dp)
            .width(250.dp)
            .background(MaterialTheme.colorScheme.background), // A more muted shade of blue
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date ?: time ?: "",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Composable
fun homeCalendar() {
    Box(
        modifier = Modifier
            .size(250.dp, 400.dp) // Adjust these values as per your desired size
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.errorContainer), // Optional: If you want a background color
        contentAlignment = Alignment.TopStart
    ) {
        DailyCalendar(date = 14, month = 10, year = 2023)
    }
}


@Composable
fun HomeSummary() {

    Row(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
//        .aspectRatio(3f)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
//                .aspectRatio(3f)
                .fillMaxSize(0.65f)
                .height(175.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer)
//            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome Home",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ButtonBox(date = "October 11, 2023")
                    ButtonBox(time = "11:30PM")
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .height(175.dp)
                .fillMaxWidth()
                .background(Color.White),
        ) {
            val progress = 0.4f

            Row {
                Column(
                    modifier = Modifier
//                        .padding(10.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Day",
                        Modifier
                            .padding(10.dp)
                            .height(20.dp))
                    Text("Week",
                        Modifier
                            .padding(10.dp)
                            .height(20.dp))
                    Text("Month",
                        Modifier
                            .padding(10.dp)
                            .height(20.dp))
                    Text("Year",
                        Modifier
                            .padding(10.dp)
                            .height(20.dp))
                }
                Column(
                    modifier = Modifier
//                        .padding(10.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        backgroundColor = Color.LightGray,
                        color = MaterialTheme.colorScheme.primary,
                        progress = progress,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(20.dp)
                            .fillMaxWidth()
                    )

                    LinearProgressIndicator(
                        backgroundColor = Color.LightGray,
                        color = MaterialTheme.colorScheme.primary,
                        progress = progress,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(20.dp)
                            .fillMaxWidth()
                    )

                    LinearProgressIndicator(
                        backgroundColor = Color.LightGray,
                        color = MaterialTheme.colorScheme.primary,
                        progress = progress,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(20.dp)
                    )

                    LinearProgressIndicator(
                        backgroundColor = Color.LightGray,
                        color = MaterialTheme.colorScheme.primary,
                        progress = progress,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(20.dp)
                            .fillMaxWidth()


                    )


                }

            }

        }


    }
}


