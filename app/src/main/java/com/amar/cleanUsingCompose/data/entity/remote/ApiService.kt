package com.amar.cleanUsingCompose.data.entity.remote

import com.amar.cleanUsingCompose.data.entity.TodoDto
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getTodos(): List<TodoDto>
}