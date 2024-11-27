package me.androidbox.todo.data.datasource.imp

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.serialization.json.Json
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import me.androidbox.todo.data.datasource.TodoRemoteDataSource
import me.androidbox.todo.data.dto.TodoDto
import org.junit.Test
import java.util.UUID
import kotlin.random.Random
import junit.framework.TestCase.assertEquals

class TodoRemoteDataSourceImpTest {
    private lateinit var todoRemoteDataSource: TodoRemoteDataSource

    @Test
    fun `should make request to fetch todo items`() = runTest {
        // Arrange
        val todoResponse = listOf(TodoDto(
            id = Random.nextInt(),
            userId = Random.nextInt(),
            title = UUID.randomUUID().toString(),
            completed = Random.nextBoolean()
        ))

        val todoResponseString = kotlinx.serialization.json.Json.encodeToString(todoResponse)
        val client = createMockEngine(todoResponseString)
        todoRemoteDataSource = TodoRemoteDataSourceImp(client)

        // Act
        val actual = todoRemoteDataSource.getTodos()

        // Assert
        val todoResult = actual.getOrNull()
        assertEquals(1, todoResult?.count())
        assertEquals(todoResponse.first().id, todoResult?.first()?.id)
    }

    private fun createMockEngine(content: String): HttpClient {
        val mockEngine = MockEngine {
            this.respond(
                content = content,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, Json.toString())
            )
        }

        val httpClient = HttpClient(mockEngine) {
            this.install(ContentNegotiation) {
                json(
                    json = kotlinx.serialization.json.Json
                )
            }
        }

        return httpClient
    }
}