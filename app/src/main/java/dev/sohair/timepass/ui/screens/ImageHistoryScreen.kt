package dev.sohair.timepass.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.sohair.timepass.data.local.CachedImage
import dev.sohair.timepass.data.local.CachedImageDao
import dev.sohair.timepass.ui.theme.Blue60
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun ImageHistoryScreen(dao: CachedImageDao) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val cachedImages = remember {
            mutableStateListOf<CachedImage>()
        }
        LaunchedEffect(key1 = true) {
            dao.getAll()?.let { cachedImages.addAll(it.reversed()) }
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        ) {
            items(cachedImages) { image ->
                AsyncImage(
                    model = image.imageUrl, contentDescription = null,
                    modifier = Modifier.size(400.dp), contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Button(
            onClick = {
                GlobalScope.launch {
                    dao.deleteAll()
                    cachedImages.clear()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Blue60),
            enabled = cachedImages.isNotEmpty()
        ) {
            Text(text = "Clear All")
        }
    }
}