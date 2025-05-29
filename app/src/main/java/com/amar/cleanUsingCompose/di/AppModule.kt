package com.amar.cleanUsingCompose.di

import com.amar.cleanUsingCompose.data.entity.remote.ApiService
import com.amar.cleanUsingCompose.data.entity.remote.TodoRemoteDataSource
import com.amar.cleanUsingCompose.data.entity.repository.TodoRemoteRepositoryImpl
import com.amar.cleanUsingCompose.domain.GetTodoListUseCase
import com.amar.cleanUsingCompose.domain.TodoRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Logs URL, headers, and body
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideTodoRemoteDataSource(apiService: ApiService): TodoRemoteDataSource=
        TodoRemoteDataSource(apiService)

    @Provides
    fun provideTodoRemoteRepository(todoRemoteDataSource: TodoRemoteDataSource): TodoRemoteRepository =
        TodoRemoteRepositoryImpl(todoRemoteDataSource)

    @Provides
    fun provideGetTodosUseCase(todoRemoteRepositoryImpl: TodoRemoteRepositoryImpl): GetTodoListUseCase =
        GetTodoListUseCase(todoRemoteRepositoryImpl)

}