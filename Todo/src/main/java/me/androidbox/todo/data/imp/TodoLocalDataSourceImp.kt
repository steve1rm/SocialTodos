package me.androidbox.todo.data.imp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.androidbox.todo.data.RealmDbClient
import me.androidbox.todo.data.TodoLocalDataSource
import me.androidbox.todo.data.entities.TodoEntity

class TodoLocalDataSourceImp(
    private val realmDbClient: RealmDbClient) : TodoLocalDataSource {
    override fun getTodos(): Flow<List<TodoEntity>> {
        return realmDbClient.realm
            .query(TodoEntity::class)
            .asFlow()
            .map { results ->
                results
                    .list
                    .toList()
            }
    }

    override suspend fun saveTodos(todoList: List<TodoEntity>) {
        return realmDbClient.realm.write {
            todoList.forEach { todoEntity ->
                this.copyToRealm(todoEntity)
            }
        }
    }

    override suspend fun updateTodo(todo: TodoEntity) {
        realmDbClient.realm.write {
            val existingTodo = this.query(TodoEntity::class, "_id == $0", todo._id)
                .first()
                .find()

            if(existingTodo != null) {
                existingTodo.id = todo.id
                existingTodo.userId = todo.userId
                existingTodo.title = todo.title
                existingTodo.completed = todo.completed
            }
            else {
                throw IllegalArgumentException("Todo [${todo.title}] with ID ${todo._id} does not exist")
            }
        }
    }
}