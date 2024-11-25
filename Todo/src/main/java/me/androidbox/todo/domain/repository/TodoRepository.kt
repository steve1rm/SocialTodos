package me.androidbox.todo.domain.repository

import me.androidbox.todo.domain.models.TodoModel

interface TodoRepository {
    suspend fun fetchTodoList(): List<TodoModel>
    suspend fun updateTodo(todoModel: TodoModel)
    suspend fun saveTodoList(todoList: List<TodoModel>)
}