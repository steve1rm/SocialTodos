package me.androidbox.todo.data.entities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class TodoEntity : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var userId: Int = 0
    var id: Int = 0
    var title: String = ""
    var completed: Boolean = false
}
