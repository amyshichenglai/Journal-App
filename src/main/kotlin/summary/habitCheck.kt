
package summary

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.EmptyBuildDrawCacheParams.size
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Shape
//import androidx.compose.material3.md.sys.shape.corner.full.Circular
import androidx.compose.ui.draw.drawBehind
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.awt.Color.red


@Composable
fun HabitCheck(habit: String) {
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
                    it[TodoTable.completed]
                )
            )
        }
    }

    var workTodo = todoListFromDb.filter { todoItem -> todoItem.section == "Work" }
    var studyTodo = todoListFromDb.filter { todoItem -> todoItem.section == "Study" }
    var hobbyTodo = todoListFromDb.filter { todoItem -> todoItem.section == "Hobby" }
    var lifeTodo = todoListFromDb.filter { todoItem -> todoItem.section == "Life" }

    var workCompleted = todoListFromDb.filter { todoItem -> todoItem.section == "Work" && todoItem.completed == true }
    var studyCompleted = todoListFromDb.filter { todoItem -> todoItem.section == "Study" && todoItem.completed == true}
    var hobbyCompleted = todoListFromDb.filter { todoItem -> todoItem.section == "Hobby" && todoItem.completed == true}
    var lifeCompleted = todoListFromDb.filter { todoItem -> todoItem.section == "Life" && todoItem.completed == true}

//    var workProgress = (todoListFromDb.count{it.section == "Work" && it.completed == true}.toDouble() / todoListFromDb.count{it.section == "Work"}.toDouble()).toFloat()
//    var studyProgress = (todoListFromDb.count{it.section == "Study" && it.completed == true}.toDouble() / todoListFromDb.count{it.section == "Study"}.toDouble()).toFloat()
//    var hobbyProgress = (todoListFromDb.count{it.section == "Hobby" && it.completed == true}.toDouble() / todoListFromDb.count{it.section == "Hobby"}.toDouble()).toFloat()
//    var lifeProgress = (todoListFromDb.count{it.section == "Life" && it.completed == true}.toDouble() / todoListFromDb.count{it.section == "Life"}.toDouble()).toFloat()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val circlePositions = listOf(
            Offset(100f, 50f),
            Offset(200f, 50f),
            Offset(300f, 50f),
            Offset(50f, 175f),
            Offset(150f, 175f),
            Offset(250f, 175f),
            Offset(350f, 175f)
        )
        val Weekday = listOf(
            "Mon",
            "Tue",
            "Wed",
            "Thu",
            "Fri",
            "Sat",
            "Sun"
        )
        val complete = listOf(
            false,
            false,
            false,
            false,
            false,
            false,
            false
        )

        listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday").forEachIndexed {index, _ ->
            complete[index] = (workTodo.count{it.datetime.dayOfWeek.toString() == "Monday"}) == (workCompleted.count{it.datetime.dayOfWeek.toString() == "Monday"})
    }

        val radius = 60f
        circlePositions.forEachIndexed {index, position ->
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .offset(position.x.dp, y=position.y.dp)
                    .drawBehind {
                        if (complete[index]) {
                            drawCircle(
                                color = Color(0xFF476810), //primary
                                radius = radius
                            )
                        } else {
                            drawCircle(
                                color = Color(0xFFC5C8B9),
                                radius = radius
                            )
                        }
                    },
                text = Weekday[index],
                color =
                    if (complete[index]) {
                        Color(0xFFFFFFFF)//onPrimary
                    } else {
                        Color.Black
                }
            )
        }
    }
}
