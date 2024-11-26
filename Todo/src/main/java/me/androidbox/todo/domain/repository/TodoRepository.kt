package me.androidbox.todo.domain.repository

import kotlinx.coroutines.flow.Flow
import me.androidbox.todo.domain.models.TodoModel

interface TodoRepository {
    suspend fun fetchRemoteTodoList()
    suspend fun updateTodo(todoModel: TodoModel)
    suspend fun saveTodoList(todoList: List<TodoModel>)
    fun fetchLocalTodoList(): Flow<List<TodoModel>>
}