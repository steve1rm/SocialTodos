package me.androidbox.todo.domain.usecases.imp

import me.androidbox.todo.domain.repository.TodoRepository
import me.androidbox.todo.domain.usecases.FetchTodoUseCase

class FetchRemoteTodoUseCaseImp(private val todoRepository: TodoRepository) : FetchTodoUseCase {
    override suspend fun execute() {
        return todoRepository.fetchRemoteTodoList()
    }
}