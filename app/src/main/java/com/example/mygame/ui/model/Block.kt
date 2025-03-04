package com.example.mygame.ui.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.UUID

data class Block(
    val topPipe: Pipe,
    val bottomPipe: Pipe,
    val hasBeenScored: Boolean = false
) {
    val id = UUID.randomUUID()
    val scoreRect =  Rect(topLeft = topPipe.rect.bottomLeft, bottomRight = bottomPipe.rect.topRight)
}

data class Pipe(
    val topY: Dp,
    val bottomY: Dp,
    val x : Dp,
    val width: Dp = 20.dp
) {
    val rect = Rect(
        topLeft = Offset(x.value, topY.value),
        bottomRight = Offset ((x + width ).value, bottomY.value))
}
