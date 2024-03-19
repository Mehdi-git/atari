package com.example.mygame.ui.engine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class TimeManager {

    val deltaTime = flow {
        while (true) {
            val time = 20
            delay(time.toLong())
            emit(time.toFloat())
        }
    }

}
