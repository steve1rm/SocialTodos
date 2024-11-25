package me.androidbox.todo.domain.usecases

import me.androidbox.todo.domain.models.TodoModel

fun interface UpdataTodoUseCase {
    suspend fun execute(todoModel: TodoModel)
}