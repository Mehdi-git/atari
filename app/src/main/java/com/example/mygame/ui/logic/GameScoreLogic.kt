package com.example.mygame.ui.logic

import com.example.mygame.ui.engine.GameLogic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameScoreLogic(
    private val playerLogic: PlayerLogic,
    private val blockLogic: BlockLogic,
): GameLogic, OnGameOverLogic {

    private val defaultScore = 0
    private val _score = MutableStateFlow(defaultScore)
    val score = _score.asStateFlow()

    override fun onUpdate(deltaTime: Float) {
        val blocks = blockLogic.blockPosition.value
        val block = blocks.first()
        if (block.hasBeenScored) return

        val scoreRect = blockLogic.blockPosition.value.first().scoreRect
        val playerRect = playerLogic.player.value.rect
        val hasScore = playerRect.overlaps(scoreRect)
        if(hasScore) {
            blockLogic.scoreBlock(block)
            _score.update { it + 1 }
        }

    }

    override fun onGameOver() {
        _score.update { defaultScore }
    }
}