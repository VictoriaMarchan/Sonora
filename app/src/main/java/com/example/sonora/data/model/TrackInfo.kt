package com.example.sonora.data.model


data class TrackInfo(
    val title: String,
    val artist: String,
    val coverRes: Int,
    val durationSeconds: Int,
    val audioResId: Int
)