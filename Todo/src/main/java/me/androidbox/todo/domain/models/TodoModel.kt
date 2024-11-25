package me.androidbox.todo.domain.models

data class TodoModel(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val completed: Boolean = false
)
