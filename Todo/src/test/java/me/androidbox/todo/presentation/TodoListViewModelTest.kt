@file:OptIn(ExperimentalCoroutinesApi::class)

package me.androidbox.todo.presentation

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.androidbox.todo.domain.models.TodoModel
import me.androidbox.todo.domain.usecases.FetchLocalTodoUseCase
import me.androidbox.todo.domain.usecases.FetchRemoteTodoUseCase
import me.androidbox.todo.domain.usecases.UpdataTodoUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.atLeast
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.UUID
import kotlin.random.Random

class TodoListViewModelTest {
    private val fetchLocalTodoUseCase = mock<FetchLocalTodoUseCase>()
    private val fetchRemoteTodoUseCase = mock<FetchRemoteTodoUseCase>()
    private val updateTodoUseCase = mock<UpdataTodoUseCase>()

    private lateinit var todoListViewModel: TodoListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        todoListViewModel = TodoListViewModel(
            fetchLocalTodoUseCase = fetchLocalTodoUseCase,
            fetchRemoteTodoUseCase = fetchRemoteTodoUseCase,
            updataTodoUseCase = updateTodoUseCase)
    }

    @Test
    fun `should collect a list of todo after subscribing`() = runTest {
        // Arrange
        val listOfTodo = generateRandomTodo()
        whenever(fetchLocalTodoUseCase.execute()).thenReturn(flow {
            emit(listOfTodo)
        })

        // Act & Assert
        todoListViewModel.todoListState.test {
            val emittedTodoList = awaitItem()
            assertEquals(listOfTodo.count(), emittedTodoList.count())
            assertEquals(listOfTodo.first().userId, emittedTodoList.first().userId)
        }
        verify(fetchLocalTodoUseCase).execute()
    }

    @Test
    fun `should collect a list of todo items after calling fetchLocalTodoItems`() = runTest {
        // Arrange
        val listOfTodo = generateRandomTodo()
        whenever(fetchLocalTodoUseCase.execute()).thenReturn(flow {
            emit(listOfTodo)
        })

        // Act
        todoListViewModel.fetchLocalTodoItems()
        advanceUntilIdle()

        // Assert
        todoListViewModel.todoListState.test {
            val emittedTodoList = awaitItem()
            assertEquals(listOfTodo.count(), emittedTodoList.count())
            assertEquals(listOfTodo.first().userId, emittedTodoList.first().userId)
        }
        verify(fetchLocalTodoUseCase, atLeast(1)).execute()
    }

    fun generateRandomTodo(): List<TodoModel> {
        return generateSequence {
            TodoModel(
                userId = Random.nextInt(),
                id = Random.nextInt(),
                title = UUID.randomUUID().toString(),
                completed = Random.nextBoolean()
            )
        }.take(10).toList()
    }
}