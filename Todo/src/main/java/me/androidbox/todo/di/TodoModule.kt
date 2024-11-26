package me.androidbox.todo.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import me.androidbox.todo.data.HttpClientFactory
import me.androidbox.todo.data.RealmDbClient
import me.androidbox.todo.data.TodoLocalDataSource
import me.androidbox.todo.data.TodoRemoteDataSource
import me.androidbox.todo.data.imp.TodoLocalDataSourceImp
import me.androidbox.todo.data.imp.TodoRemoteDataSourceImp
import me.androidbox.todo.data.repository.imp.TodoRepositoryImp
import me.androidbox.todo.domain.repository.TodoRepository
import me.androidbox.todo.domain.usecases.FetchLocalTodoUseCase
import me.androidbox.todo.domain.usecases.FetchTodoUseCase
import me.androidbox.todo.domain.usecases.UpdataTodoUseCase
import me.androidbox.todo.domain.usecases.imp.FetchLocalTodoUseCaseImp
import me.androidbox.todo.domain.usecases.imp.FetchRemoteTodoUseCaseImp
import me.androidbox.todo.domain.usecases.imp.UpdateTodoUseCaseImp
import me.androidbox.todo.presentation.TodoListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
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

    factory<TodoLocalDataSource> {
        TodoLocalDataSourceImp(get<RealmDbClient>())
    }

    factory<TodoRepository> {
        TodoRepositoryImp(
            get<TodoLocalDataSource>(),
            get<TodoRemoteDataSource>()
        )
    }

    factory<FetchTodoUseCase> {
        FetchRemoteTodoUseCaseImp(get<TodoRepository>())
    }
    factoryOf(::UpdateTodoUseCaseImp).bind(UpdataTodoUseCase::class)
    factoryOf(::FetchLocalTodoUseCaseImp).bind(FetchLocalTodoUseCase::class)

    viewModelOf(::TodoListViewModel)
}