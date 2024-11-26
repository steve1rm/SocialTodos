package me.androidbox.todo.domain.usecases

fun interface FetchRemoteTodoUseCase {
    suspend fun execute()
}