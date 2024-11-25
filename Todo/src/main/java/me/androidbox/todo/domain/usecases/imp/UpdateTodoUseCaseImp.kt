package me.androidbox.todo.domain.usecases.imp

import me.androidbox.todo.domain.models.TodoModel
import me.androidbox.todo.domain.repository.TodoRepository
import me.androidbox.todo.domain.usecases.UpdataTodoUseCase

class UpdateTodoUseCaseImp(private val todoRepository: TodoRepository) : UpdataTodoUseCase {
    override suspend fun execute(todoModel: TodoModel) {
        todoRepository.updateTodo(todoModel)
    }
}