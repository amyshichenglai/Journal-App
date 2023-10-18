package note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NoteList() {
    val selectedNoteIndex = remember { mutableStateOf<Int?>(null) }

    LazyColumn() {
        items(20) {
            val backgroundColor = if (it == selectedNoteIndex.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onPrimary
            val fontWeight = if (it == selectedNoteIndex.value) FontWeight.Bold else FontWeight.Normal

            ElevatedCard (
                // shadow
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = backgroundColor
                ),
                shape = RoundedCornerShape(8.dp),
            ) {
                Row (modifier = Modifier
                    .clickable { selectedNoteIndex.value = it }
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                ) {
                    Icon(
                        Icons.Filled.Edit, // icon image
                        contentDescription = "A Pen",
                        modifier = Modifier
                            .clickable {  }
                            .align(Alignment.CenterVertically)
                            .padding(start = 10.dp)
                    )
                    Text(
                        text = "Note ${it+1}",
                        fontWeight = fontWeight,
                        modifier = Modifier
                            .padding(16.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}