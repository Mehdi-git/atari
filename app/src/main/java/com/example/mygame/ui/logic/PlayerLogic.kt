package com.example.mygame.ui.logic

import com.example.mygame.ui.model.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerLogic {
    private val _playerPosition = MutableStateFlow(Player(100f, 0f))
    val playerPosition: StateFlow<Player> = _playerPosition
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val deltaTime = flow {
        while (true) {
            val time = 5
            delay(time.toLong())
            emit(time.toFloat())
        }
    }

    init {
        coroutineScope.launch {
            deltaTime.collect { dt ->
                _playerPosition.update { player ->
                    var newY = player.y + player.speed * dt + 0.5 * Player.acceleration * dt * dt
                    var newSpeed = player.speed + Player.acceleration * dt
                    if (newY > 2000f) {
                        newY = 0.0
                        newSpeed = 0f
                    }
                    player.copy(y = newY.toFloat(), speed = newSpeed)
                }
            }
        }
    }


    fun jump() {
        _playerPosition.update {it.copy( speed = -1f)}
    }

}