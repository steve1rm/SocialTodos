package me.androidbox.todo.domain.usecases.imp

import me.androidbox.todo.domain.repository.TodoRepository
import me.androidbox.todo.domain.usecases.FetchRemoteTodoUseCase

class FetchRemoteTodoUseCaseImp(private val todoRepository: TodoRepository) : FetchRemoteTodoUseCase {
    override suspend fun execute() {
        return todoRepository.fetchRemoteTodoList()
    }
}