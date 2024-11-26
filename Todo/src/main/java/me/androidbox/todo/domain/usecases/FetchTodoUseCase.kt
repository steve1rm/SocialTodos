package me.androidbox.todo.domain.usecases

fun interface FetchTodoUseCase {
    suspend fun execute()
}