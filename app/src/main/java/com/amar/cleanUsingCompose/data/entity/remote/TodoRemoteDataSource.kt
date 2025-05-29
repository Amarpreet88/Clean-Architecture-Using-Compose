package com.amar.cleanUsingCompose.data.entity.remote

import com.amar.cleanUsingCompose.data.entity.TodoDto
import com.amar.cleanUsingCompose.domain.common.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoRemoteDataSource @Inject constructor(private val apiService: ApiService) {
     fun getTodos(): Flow<ResultState<List<TodoDto>>> = flow {
          emit(ResultState.Loading)
          try{
               val todos = apiService.getTodos()
               emit(ResultState.Success(todos))
          } catch (e: Exception){
               emit(ResultState.Failure("Failed to load: ${e.message}", ""))
          }
     }
}