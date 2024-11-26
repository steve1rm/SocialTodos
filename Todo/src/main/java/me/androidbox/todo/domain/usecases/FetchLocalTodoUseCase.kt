package me.androidbox.todo.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.androidbox.todo.domain.models.TodoModel

fun interface FetchLocalTodoUseCase {
    fun execute(): Flow<List<TodoModel>>
}