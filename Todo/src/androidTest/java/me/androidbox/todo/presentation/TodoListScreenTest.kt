package me.androidbox.todo.presentation

import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import me.androidbox.todo.domain.models.TodoModel
import org.junit.Rule
import org.junit.Test
import java.util.UUID
import kotlin.random.Random

class TodoListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun should_display_the_list_of_todo_items() {
        val listOfTodo = generateRandomTodo()

        composeTestRule.setContent {
            TodoListScreen(
                todoModel = listOfTodo
            )
        }
        val todo = listOfTodo.first()
        composeTestRule.onNodeWithText(todo.userId.toString())
        composeTestRule.onNodeWithText(todo.title.toString())

        val switchNode = composeTestRule.onNode(hasTestTag("switch_${todo.id}"))
        if(todo.completed) {
            switchNode.assertIsOn()
        }
        else {
            switchNode.assertIsOff()
        }

        val iconNode = composeTestRule.onNode(hasTestTag("icon_${todo.id}"))
        iconNode.assertExists()
    }

    fun generateRandomTodo(): List<TodoModel> {
        return generateSequence {
            TodoModel(
                userId = Random.nextInt(),
                id = Random.nextInt(),
                title = UUID.randomUUID().toString(),
                completed = Random.nextBoolean()
            )
        }.take(10).toList()
    }
}