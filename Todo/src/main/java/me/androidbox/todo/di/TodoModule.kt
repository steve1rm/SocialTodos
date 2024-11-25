package me.androidbox.todo.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import me.androidbox.todo.data.HttpClientFactory
import me.androidbox.todo.data.RealmDbClient
import me.androidbox.todo.data.TodoRemoteDataSource
import me.androidbox.todo.data.imp.TodoRemoteDataSourceImp
import org.koin.dsl.module

val todoModule = module {
    single<HttpClient> {
        HttpClientFactory.build(CIO.create())
    }

    single<RealmDbClient> {
        RealmDbClient()
    }

    factory<TodoRemoteDataSource> {
        TodoRemoteDataSourceImp(get<HttpClient>())
    }
}