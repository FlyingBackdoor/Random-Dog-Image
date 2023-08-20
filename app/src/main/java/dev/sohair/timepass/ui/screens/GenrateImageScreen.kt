package dev.sohair.timepass.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.disk.DiskCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import dev.sohair.timepass.data.local.CachedImage
import dev.sohair.timepass.data.local.CachedImageDao
import dev.sohair.timepass.ui.theme.Blue60
import dev.sohair.timepass.utils.ImageUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun GenerateImageScreen(dao: CachedImageDao) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val scope = CoroutineScope(Dispatchers.IO)
        val context = LocalContext.current
        var imageUrl by remember {
            mutableStateOf("")
        }
        var buttonEnabled by remember {
            mutableStateOf(true)
        }

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .heightIn(max = 300.dp)
                .padding(4.dp),
            loading = { CircularProgressIndicator() },

        )
        Spacer(modifier = Modifier.height(120.dp))
        Button(
            onClick = {
                scope.launch {
                    try {
                        buttonEnabled = false
                        val imageResult = ImageUtils.getRandomImage()
                        imageUrl = imageResult.message ?: ""
                        dao.insertImage(CachedImage(imageUrl = imageUrl))
                        buttonEnabled = true
                    } catch (e: Exception) {
                        e.printStackTrace()
                        buttonEnabled = true
                    }
                }
            },
            enabled = buttonEnabled,
            colors = ButtonDefaults.buttonColors(containerColor = Blue60)
        ) {
            Text(text = "Generate")
        }
    }
}