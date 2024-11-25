package me.androidbox.todo.domain.repository

import kotlinx.coroutines.flow.Flow
import me.androidbox.todo.domain.models.TodoModel

interface TodoRepository {
    suspend fun fetchTodoList(): Flow<List<TodoModel>>
    suspend fun updateTodo(todoModel: TodoModel)
    suspend fun saveTodoList(todoList: List<TodoModel>)
}