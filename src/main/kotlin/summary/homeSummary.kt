package summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

//barchart
//https://github.com/developerchunk/BarGraph-JetpackCompose/tree/main/app/src/main/java/com/example/customchar

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
                .background(Color.White),
//            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    text = "Welcome Home",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                Row {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .height(50.dp)
                            .width(250.dp)
                            .background(Color.Blue),
                        contentAlignment = Alignment.Center
                    ) {
                        val date = "October 11, 2023"
                        Text(
                            text = date,
//                            fontFamily = FontFamily.Cursive,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
//                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .height(50.dp)
                            .width(250.dp)
                            .background(Color.Blue),
                        contentAlignment = Alignment.Center
                    ) {
                        val time = "11:30PM         "
                        Text(
                            text = time,
//                            fontFamily = FontFamily.Cursive,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
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
                        progress = progress,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(20.dp)
                            .fillMaxWidth()
                    )

                    LinearProgressIndicator(
                        backgroundColor = Color.LightGray,
                        progress = progress,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(20.dp)
                            .fillMaxWidth()
                    )

                    LinearProgressIndicator(
                        backgroundColor = Color.LightGray,
                        progress = progress,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(20.dp)
                            .fillMaxWidth()
                    )

                    LinearProgressIndicator(
                        backgroundColor = Color.LightGray,
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


