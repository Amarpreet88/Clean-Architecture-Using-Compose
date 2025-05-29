package com.amar.cleanUsingCompose.presenentation

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.amar.cleanUsingCompose.data.entity.TodoDto
import com.amar.cleanUsingCompose.domain.common.ResultState
import com.amar.cleanUsingCompose.ui.theme.TestApplicationTheme

@Composable
fun TodoScreen(navController: NavController,
               viewModel: TodoViewModel = hiltViewModel()) {
    val uiState by viewModel.todos.collectAsState()
    TestApplicationTheme {
            when (uiState) {
                is ResultState.Loading -> LoadingScreen()
                is ResultState.Success ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(items = (uiState as ResultState.Success<List<TodoDto>>).data) { todo ->
                            ItemRow(todo = todo) {
                                val description = Uri.encode(todo.description)
                                val image = Uri.encode(todo.image)
                                navController.navigate("detail/${description}/${image}")
                            }
                        }
                    }
                is ResultState.Failure -> ErrorScreen("Network failure")
            }
        }
    }


@Composable
fun ItemRow(todo: TodoDto, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }

    ) {
        Image(painter = rememberAsyncImagePainter(todo.image),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(40.dp)
                .clip(RoundedCornerShape(12.dp)))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = todo.title, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
    }
}