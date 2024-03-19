package com.example.mygame.ui.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect

data class Block(val topPipe: Pipe, val bottomPipe: Pipe) {
    val scoreRect =  Rect(topLeft = topPipe.rect.bottomLeft, bottomRight = bottomPipe.rect.topRight)
}


data class Pipe(
    val topY: Float,
    val bottomY: Float,
    val x : Float,
    val width: Float = 20f
) {
    val rect = Rect(topLeft = Offset(x, topY), bottomRight = Offset (x + width , bottomY))
}
