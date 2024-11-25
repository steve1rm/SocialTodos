package me.androidbox.socialtodos.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.todo.presentation.TodoListScreen
import me.androidbox.todo.presentation.TodoListViewModel
import org.koin.androidx.compose.koinViewModel

data object TodoListScreenRoute : Screen {
    @Composable
    override fun Content() {
        val todoListViewModel = koinViewModel<TodoListViewModel>()

        TodoListScreen()
    }
}