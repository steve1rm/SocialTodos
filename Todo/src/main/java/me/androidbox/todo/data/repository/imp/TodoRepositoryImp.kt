package me.androidbox.todo.data.repository.imp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.androidbox.todo.data.TodoLocalDataSource
import me.androidbox.todo.data.TodoRemoteDataSource
import me.androidbox.todo.data.entities.TodoEntity
import me.androidbox.todo.domain.repository.TodoRepository
import me.androidbox.todo.domain.models.TodoModel

class TodoRepositoryImp(
    private val todoLocalDataSource: TodoLocalDataSource,
    private val todoRemoteDataSource: TodoRemoteDataSource
) : TodoRepository {
    override suspend fun fetchTodoList(): Flow<List<TodoModel>> {
        val result = todoRemoteDataSource.getTodos()

        result.fold(
            onSuccess = { listOfTodoDts ->
                /** Simple mapper to map to the entities for insertion into realm */
                val todoEntities = listOfTodoDts.map { todoDto ->
                    TodoEntity().apply {
                        id = todoDto.id
                        userId = todoDto.userId
                        title = todoDto.title
                        completed = todoDto.completed
                    }
                }

                /** Insertion into Realm as the source of truth */
                todoLocalDataSource.saveTodos(todoEntities)

                /** Fetch from realm after insertion and uses a simple mapper to return the TodoModel */
                return todoLocalDataSource.getTodos().map { listTodoEntity ->
                    listTodoEntity.map { entity ->
                        TodoModel(
                            id = entity.id,
                            userId = entity.userId,
                            title = entity.title,
                            completed = entity.completed
                        )
                    }
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