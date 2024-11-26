package me.androidbox.todo.data.datasource

import me.androidbox.todo.data.dto.TodoDto

interface TodoRemoteDataSource {
    suspend fun getTodos(): Result<List<TodoDto>>
}