package com.example.mygame.ui.model

data class Player(val y: Float, val speed: Float) {
    companion object {
       const val acceleration = 0.002f
    }
}
