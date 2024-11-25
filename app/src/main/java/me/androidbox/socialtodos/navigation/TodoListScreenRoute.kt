package me.androidbox.socialtodos.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.todo.presentation.TodoListScreen

data object TodoListScreenRoute : Screen {
    @Composable
    override fun Content() {
        TodoListScreen()
    }
}