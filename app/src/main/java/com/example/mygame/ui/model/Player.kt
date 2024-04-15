package com.example.mygame.ui.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect

data class Player(
    val y: Float,
    val speed: Float,
    val size: Size = Size(70f, 40f)
) {

    val rect = Rect(Offset(0f,y), size.toComposeSize())
    companion object {
        const val acceleration = 0.002f
    }
}

