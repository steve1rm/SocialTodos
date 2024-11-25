package me.androidbox.todo.data.repository.imp

import me.androidbox.todo.data.TodoLocalDataSource
import me.androidbox.todo.data.TodoRemoteDataSource
import me.androidbox.todo.domain.repository.TodoRepository
import me.androidbox.todo.domain.models.TodoModel

class TodoRepositoryImp(
    private val todoLocalDataSource: TodoLocalDataSource,
    private val todoRemoteDataSource: TodoRemoteDataSource
) : TodoRepository {
    override suspend fun fetchTodoList(): List<TodoModel> {
        val result = todoRemoteDataSource.getTodos()

        result.fold(
            onSuccess = { listOfTodoDts ->
                return listOfTodoDts.map { todoDto ->
                    TodoModel(
                        id = todoDto.id,
                        userId = todoDto.userId,
                        title = todoDto.title,
                        completed = todoDto.completed
                    )
                }
            },
            onFailure = { failure ->
                throw failure
            }
        )
    }

    override suspend fun updateTodo(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override suspend fun saveTodoList(todoList: List<TodoModel>) {
        TODO("Not yet implemented")
    }
}