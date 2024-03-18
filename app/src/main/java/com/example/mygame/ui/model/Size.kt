package com.example.mygame.ui.model

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class Size(val width: Float, val height : Float)


fun Size.ToDpSize(): DpSize =  DpSize(width.dp, height.dp)






