package me.androidbox.todo.domain.usecases.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.todo.domain.models.TodoModel
import me.androidbox.todo.domain.repository.TodoRepository
import me.androidbox.todo.domain.usecases.FetchLocalTodoUseCase

class FetchLocalTodoUseCaseImp(private val todoRepository: TodoRepository) : FetchLocalTodoUseCase {
    override fun execute(): Flow<List<TodoModel>> {
        return todoRepository.fetchLocalTodoList()
    }
}