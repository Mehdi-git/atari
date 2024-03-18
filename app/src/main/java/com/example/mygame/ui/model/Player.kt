package com.example.mygame.ui.model

data class Player(val y: Float, val speed: Float, val size: Size = Size(90f, 30f)) {
    companion object {
        const val acceleration = 0.002f
    }
}

