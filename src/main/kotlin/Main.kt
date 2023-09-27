import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = application {
    Window(
        title = "Client-Server Demo",
        onCloseRequest = ::exitApplication,
        state = WindowState(size =  DpSize(300.dp, 250.dp))
    ) {
        MaterialTheme {
            var result: String = "empty"
            val site = "https://ktor.io/"

            runBlocking {
                launch {
                    result = query(site)
                }
            }
            Text("${site}: ${result}")
        }
    }
}

suspend fun query(site:String): String {
    val client = HttpClient(CIO)
    val response: HttpResponse = client.get(site)
    client.close()
    return("${response.status}")
}