package me.androidbox.todo.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.todo.domain.models.TodoModel

@Composable
fun TodoListScreen(
    todoModel: List<TodoModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = todoModel,
            key = { todoModel ->
                todoModel.id
            },
            itemContent = { todoModel ->
                TodoItem(
                    todoModel = todoModel
                )
            }
        )
    }
}

@Composable
fun TodoItem(
    todoModel: TodoModel,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "User ID: ${todoModel.userId}",
                    fontSize = 16.sp)

                Switch(
                    modifier = Modifier.testTag("switch_${todoModel.id}"),
                    checked = todoModel.completed,
                    onCheckedChange = { isCompleted ->
                        /** Update local DB */
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Title: ${todoModel.title}",
                    maxLines = 1,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis)

                Icon(
                    modifier = Modifier.testTag("icon_${todoModel.id}"),
                    imageVector = if (todoModel.completed) Icons.Default.Done else Icons.Default.Close,
                    contentDescription = null,
                    tint = if (todoModel.completed) Color.Green else Color.Red
                )
            }
        }
    }
}

@Preview
@Composable
fun TodoItemPreview() {
    val todoModel = TodoModel(
        userId = 1,
        id = 1,
        title = "explicabo consectetur debitis voluptates quas quae culpa rerum non",
        completed = false
    )

    TodoItem(todoModel = todoModel, modifier = Modifier)
}

class SampleTodoModelProvider : PreviewParameterProvider<List<TodoModel>> {
    override val values: Sequence<List<TodoModel>>
        get() = sequenceOf(
            listOf(
                TodoModel(1, 6, "Task 1", false),
                TodoModel(2, 6, "Task 2", true),
                TodoModel(3, 6, "Task 3", false),
                TodoModel(4, 6, "Task 4", true)
            ),
            emptyList()
        )
}
@Preview
@Composable
fun TodoListScreenPreview(
    @PreviewParameter(SampleTodoModelProvider::class) todoModel: List<TodoModel>
) {
    TodoListScreen(todoModel = todoModel)
}