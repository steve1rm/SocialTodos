package me.androidbox.todo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.androidbox.todo.domain.models.TodoModel
import me.androidbox.todo.domain.usecases.FetchLocalTodoUseCase
import me.androidbox.todo.domain.usecases.FetchRemoteTodoUseCase
import me.androidbox.todo.domain.usecases.UpdataTodoUseCase

class TodoListViewModel(
    private val fetchLocalTodoUseCase: FetchLocalTodoUseCase,
    private val fetchRemoteTodoUseCase: FetchRemoteTodoUseCase,
    private val updataTodoUseCase: UpdataTodoUseCase
) : ViewModel() {

    private val _todoListState = MutableStateFlow(emptyList<TodoModel>())
    val todoListState = _todoListState.asStateFlow()
        .onStart {
            fetchLocalTodoItems()
            println("fetchLocalTodoItems onStart")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    fun fetchLocalTodoItems() {
        viewModelScope.launch {
            launch {
                fetchLocalTodoUseCase.execute()
                    .collect { todoList ->
                        _todoListState.update {
                            todoList
                        }
                    }
            }

            launch {
                try {
                    fetchRemoteTodoUseCase.execute()
                }
                catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }

    }
}
