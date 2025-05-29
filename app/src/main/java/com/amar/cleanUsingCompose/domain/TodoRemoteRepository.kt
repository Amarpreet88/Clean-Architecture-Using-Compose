package com.amar.cleanUsingCompose.domain

import com.amar.cleanUsingCompose.data.entity.TodoDto
import com.amar.cleanUsingCompose.domain.common.ResultState
import kotlinx.coroutines.flow.Flow

interface TodoRemoteRepository {
     fun getTodos(): Flow<ResultState<List<TodoDto>>>
}