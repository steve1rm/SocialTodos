package me.androidbox.todo.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import me.androidbox.todo.data.HttpClientFactory
import org.koin.dsl.module

val todoModule = module {
    single<HttpClient> {
        HttpClientFactory.build(CIO.create())
    }
}