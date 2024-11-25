package me.androidbox.todo.domain.usecases.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.todo.domain.models.TodoModel
import me.androidbox.todo.domain.repository.TodoRepository
import me.androidbox.todo.domain.usecases.FetchTodoUseCase

class FetchTodoUseCaseImp(private val todoRepository: TodoRepository) : FetchTodoUseCase {
    override suspend fun execute(): Flow<List<TodoModel>> {
        return todoRepository.fetchTodoList()
    }
}