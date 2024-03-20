package com.example.mygame.ui.logic

import com.example.mygame.ui.engine.GameLogic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameScoreLogic(
    private val playerLogic: PlayerLogic,
    private val blockLogic: BlockLogic
): GameLogic {

    private val _score = MutableStateFlow(2)
    val score = _score.asStateFlow()

    override fun onUpdate(deltaTime: Float) {
        val scoreRect = blockLogic.blockPosition.value.scoreRect
        val playerRect = playerLogic.player.value.rect
        val hasScore = playerRect.overlaps(scoreRect)
        if(hasScore) {
            _score.update { it + 1 }
        }

    }
}