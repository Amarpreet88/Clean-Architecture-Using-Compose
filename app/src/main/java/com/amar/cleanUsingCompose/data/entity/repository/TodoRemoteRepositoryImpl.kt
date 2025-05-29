package com.amar.cleanUsingCompose.data.entity.repository

import com.amar.cleanUsingCompose.data.entity.TodoDto
import com.amar.cleanUsingCompose.data.entity.remote.TodoRemoteDataSource
import com.amar.cleanUsingCompose.domain.TodoRemoteRepository
import com.amar.cleanUsingCompose.domain.common.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRemoteRepositoryImpl @Inject constructor(private val todoRemoteDataSource: TodoRemoteDataSource) :
    TodoRemoteRepository {
    override fun getTodos(): Flow<ResultState<List<TodoDto>>> {
        return todoRemoteDataSource.getTodos()
    }
}