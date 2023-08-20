package dev.sohair.timepass.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ImageResult(
    val message: String?,
    val status: String?
)
