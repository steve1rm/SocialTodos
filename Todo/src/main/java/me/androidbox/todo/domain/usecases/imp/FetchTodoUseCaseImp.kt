package me.androidbox.todo.domain.usecases.imp

import me.androidbox.todo.domain.models.TodoModel
import me.androidbox.todo.domain.repository.TodoRepository
import me.androidbox.todo.domain.usecases.FetchTodoUseCase

class FetchTodoUseCaseImp(private val todoRepository: TodoRepository) : FetchTodoUseCase {
    override suspend fun execute(): List<TodoModel> {
        return todoRepository.fetchTodoList()
    }
}