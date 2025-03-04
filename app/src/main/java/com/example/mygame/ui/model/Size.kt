package com.example.mygame.ui.model

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class Size(val width: Float, val height : Float)


fun Size.toDpSize(): DpSize {
    return DpSize(width.dp, height.dp)
}

fun Size.toComposeSize () : androidx.compose.ui.geometry.Size {
    return androidx.compose.ui.geometry.Size(width, height)
}






