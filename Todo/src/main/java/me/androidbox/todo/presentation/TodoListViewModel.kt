package me.androidbox.todo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.androidbox.todo.domain.usecases.FetchTodoUseCase
import me.androidbox.todo.domain.usecases.UpdataTodoUseCase

class TodoListViewModel(
    private val fetchTodoUseCase: FetchTodoUseCase,
    private val updataTodoUseCase: UpdataTodoUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            try {
                val todoList = fetchTodoUseCase.execute()
                println(todoList)
            }
            catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}