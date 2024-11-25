package me.androidbox.todo.domain.usecases

import me.androidbox.todo.domain.models.TodoModel

fun interface FetchTodoUseCase {
    suspend fun execute(): List<TodoModel>
}