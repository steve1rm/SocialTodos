package me.androidbox.socialtodos.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.todo.presentation.TodoListScreen
import me.androidbox.todo.presentation.TodoListViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue

data object TodoListScreenRoute : Screen {
    @Composable
    override fun Content() {
        val todoListViewModel = koinViewModel<TodoListViewModel>()

        val todoListState by todoListViewModel.todoListState.collectAsStateWithLifecycle()

        TodoListScreen(
            todoModel = todoListState
        )
    }
}