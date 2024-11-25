package me.androidbox.todo.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TodoDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
