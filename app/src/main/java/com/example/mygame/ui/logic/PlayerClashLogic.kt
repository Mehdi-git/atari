package com.example.mygame.ui.logic

import androidx.compose.ui.unit.dp
import com.example.mygame.ui.engine.GameLogic
import com.example.mygame.ui.model.Pipe
import com.example.mygame.ui.model.Player
import com.example.mygame.ui.model.Viewport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayerClashLogic (
    private val playerLogic: PlayerLogic,
    private val blockMovementLogic: BlockMovementLogic,
    private val gameStatusLogic: GameStatusLogic,
    private val viewport: Viewport
): GameLogic {

    private val _clashHappened = MutableStateFlow(false)
    val clashHappened = _clashHappened.asStateFlow()
    override fun onUpdate(deltaTime: Float) {
        checkBlockCollision()
        checkFallDown()
    }
    private fun checkBlockCollision() {
        val player = playerLogic.player.value
        val blocks = blockMovementLogic.blockPosition.value
        blocks.forEach { block ->
            val topPipeClash = clashed(player, block.topPipe)
            val bottomPipeClash = clashed(player, block.bottomPipe)
            val clashHappened = topPipeClash || bottomPipeClash
            if (clashHappened) {
                gameStatusLogic.gameOver()
            }
            _clashHappened.value = clashHappened
        }
    }

    private fun checkFallDown(){
        if(playerLogic.player.value.y.dp > viewport.height) {
            gameStatusLogic.gameOver()
        }
    }

    private fun clashed (
        player: Player,
        pipe: Pipe
    ) = player.rect.overlaps(pipe.rect)
}