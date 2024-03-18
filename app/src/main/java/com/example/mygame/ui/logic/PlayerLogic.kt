package com.example.mygame.ui.logic

import com.example.mygame.ui.model.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerLogic(
    private val timeManager: TimeManager,
    private val coroutineScope: CoroutineScope
) {

    private val _player = MutableStateFlow(Player(100f, 0f))
    val player: StateFlow<Player> = _player

    init {
        coroutineScope.launch {
            timeManager.deltaTime.collect { dt ->
                _player.update { player ->
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
        _player.update { it.copy(speed = -0.5f) }
    }

}