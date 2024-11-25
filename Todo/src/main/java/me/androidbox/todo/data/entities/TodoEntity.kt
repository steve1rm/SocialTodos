package me.androidbox.todo.data.entities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class TodoEntity : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var userId: Int = 0
    var title: String = ""
    var completed: Boolean = false
}
