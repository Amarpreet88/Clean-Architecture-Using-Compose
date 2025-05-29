package com.amar.cleanUsingCompose.presenentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun TodoDetailScreen(description: String, url: String) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = rememberAsyncImagePainter(url),
            contentDescription = "image",
            modifier = Modifier
                .size(200.dp)
                .padding(30.dp)
                .clip(shape = RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = description, style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(30.dp)
        )

    }

}