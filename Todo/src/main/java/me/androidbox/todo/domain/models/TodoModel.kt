package me.androidbox.todo.domain.models

data class TodoModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
