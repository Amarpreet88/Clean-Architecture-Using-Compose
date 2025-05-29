package com.amar.cleanUsingCompose.domain

import com.amar.cleanUsingCompose.data.entity.TodoDto
import com.amar.cleanUsingCompose.domain.common.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(private val todoRemoteRepository: TodoRemoteRepository) {
    operator fun invoke(): Flow<ResultState<List<TodoDto>>> = todoRemoteRepository.getTodos()
}