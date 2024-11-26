package me.androidbox.todo.data.datasource.imp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.ensureActive
import me.androidbox.todo.data.datasource.TodoRemoteDataSource
import me.androidbox.todo.data.dto.TodoDto
import kotlin.coroutines.coroutineContext

class TodoRemoteDataSourceImp(
    private val httpClient: HttpClient
) : TodoRemoteDataSource {

    override suspend fun getTodos(): Result<List<TodoDto>> {
        try {
            val listOfTodos = httpClient.get(
                urlString = "https://jsonplaceholder.typicode.com/todos"
            ).body<List<TodoDto>>()

            return Result.success(listOfTodos)
        }
        catch (exception: Exception) {
            coroutineContext.ensureActive()
            exception.printStackTrace()

            return Result.failure(exception)
        }
    }
}