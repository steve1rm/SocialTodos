package me.androidbox.todo.data.datasource

import kotlinx.coroutines.flow.Flow
import me.androidbox.todo.data.entities.TodoEntity

interface TodoLocalDataSource {
    fun getTodos(): Flow<List<TodoEntity>>
    suspend fun saveTodos(todoList: List<TodoEntity>)
    suspend fun updateTodo(todo: TodoEntity)
}