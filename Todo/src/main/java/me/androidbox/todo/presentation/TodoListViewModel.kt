package me.androidbox.todo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.androidbox.todo.domain.models.TodoModel
import me.androidbox.todo.domain.usecases.FetchTodoUseCase
import me.androidbox.todo.domain.usecases.UpdataTodoUseCase

class TodoListViewModel(
    private val fetchTodoUseCase: FetchTodoUseCase,
    private val updataTodoUseCase: UpdataTodoUseCase
) : ViewModel() {

    private val _todoListState = MutableStateFlow(emptyList<TodoModel>())
    val todoListState = _todoListState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                fetchTodoUseCase.execute()
                    .collect { todoList ->
                        _todoListState.update {
                            println("TodoListViewModel ${it.count()}")
                            todoList
                        }
                    }
            }
            catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}