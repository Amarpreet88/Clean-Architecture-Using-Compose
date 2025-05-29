package com.amar.cleanUsingCompose.data.entity

data class TodoDto(
    var id: Int,
    val title: String,
    val price: String,
    val description: String,
    val category: String,
    var image: String
)
