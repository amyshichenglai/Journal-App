import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WeeklyReport() {
    Text("weekly")
}

@Composable
fun MonthlyReport() {
    Text("monthly")
}

@Composable
fun AnnualReport() {
    Text("annual")
}
@Composable
fun Summary() {
    var progress by remember { mutableStateOf(0.1f) }
    val (selectedSection, setSelectedSection) = remember { mutableStateOf("Section 1") }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
//            modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { setSelectedSection("Weekly") }, modifier = Modifier.weight(1f).padding(16.dp)) {
                    Text("Weekly")
                }
                Button(onClick = { setSelectedSection("Monthly") }, modifier = Modifier.weight(1f).padding(16.dp)) {
                    Text("Monthly")
                }
                Button(onClick = { setSelectedSection("Annual") }, modifier = Modifier.weight(1f).padding(16.dp)) {
                    Text("Annual")
                }
            }
        }
        item {
            Box(
//            modifier = Modifier.weight(3f).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                when (selectedSection) {
                    "Weekly" -> WeeklyReport()
                    "Monthly" -> MonthlyReport()
                    "Annual" -> AnnualReport()
                }
            }
        }

        item {
            LinearProgressIndicator(
                backgroundColor = Color.White,
                progress = progress,
                modifier = Modifier
                    .padding(16.dp) // Add some padding for spacing
            )
        }

        item {
            CircularProgressIndicator(
                backgroundColor = Color.White,
                progress = progress,
                modifier = Modifier
                    .padding(16.dp) // Add some padding for spacing
            )
        }
    }
}

//barchart
//https://github.com/developerchunk/BarGraph-JetpackCompose/tree/main/app/src/main/java/com/example/customchar
