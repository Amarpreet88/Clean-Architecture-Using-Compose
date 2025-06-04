package com.amar.cleanUsingCompose

import com.amar.cleanUsingCompose.data.entity.TodoDto
import com.amar.cleanUsingCompose.domain.GetTodoListUseCase
import com.amar.cleanUsingCompose.domain.common.ResultState
import com.amar.cleanUsingCompose.presenentation.TodoViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TodoViewModelTest {

    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: TodoViewModel
    private val getTodoListUseCase: GetTodoListUseCase = mockk()

    @Before
    fun setup(){


        every { getTodoListUseCase() } returns flow {
            val items = listOf(
                TodoDto(category = "A", id = 1, image = "www.google.com", price = "100", title = "item1", description = "item1"),
                TodoDto(category = "A", id = 1, image = "www.google.com", price = "100", title = "item1", description = "item1"))
            emit(ResultState.Success(items))
        }
        viewModel = TodoViewModel(getTodoListUseCase)
    }

    @Test
    fun `emit success with list`() = runTest {
        val result = viewModel.todos.first()
        println("Result: $result")  // Debug print

//        assert(result is ResultState.Success)
        val success = result as ResultState.Success
        assert(success.data.size == 1)
        assert(success.data.first().title.equals("item1"))

    }
}