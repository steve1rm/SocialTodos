package me.androidbox.todo.data

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import me.androidbox.todo.data.entities.TodoEntity

class RealmDbClient {
    var realm: Realm = Realm.open(
        configuration = RealmConfiguration.create(
            schema = setOf(
                TodoEntity::class
        ))
    )
}