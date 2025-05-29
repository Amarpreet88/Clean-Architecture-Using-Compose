package com.amar.cleanUsingCompose.presenentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amar.cleanUsingCompose.data.entity.TodoDto
import com.amar.cleanUsingCompose.domain.GetTodoListUseCase
import com.amar.cleanUsingCompose.domain.common.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    getTodoListUseCase: GetTodoListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ResultState<List<TodoDto>>>(ResultState.Loading)

    val todos: StateFlow<ResultState<List<TodoDto>>> =
        getTodoListUseCase()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ResultState.Loading
            )
}
