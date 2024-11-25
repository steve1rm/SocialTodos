package me.androidbox.socialtodos

import android.app.Application
import me.androidbox.todo.di.todoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class SocialTodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SocialTodoApplication)
            androidLogger()

            modules(
                todoModule
            )
        }
    }
}