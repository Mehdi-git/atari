package com.example.mygame.ui.logic

import com.example.mygame.ui.engine.GameLogic
import com.example.mygame.ui.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PlayerLogic(private val gameStatus: GameStatusLogic): GameLogic {

    private val _player = MutableStateFlow(Player(100f, 0f))
    val player: StateFlow<Player> = _player


    fun jump() {
        gameStatus.gameStarted()
        _player.update { it.copy(speed = -0.5f) }
    }

    override fun onUpdate(deltaTime: Float) {
        _player.update { player ->
            var newY = player.y + player.speed * deltaTime + 0.5 * Player.acceleration * deltaTime * deltaTime
            var newSpeed = player.speed + Player.acceleration * deltaTime
            if (newY > 2000f) {
                newY = 0.0
                newSpeed = 0f
            }
            player.copy(y = newY.toFloat(), speed = newSpeed)
        }
    }

}