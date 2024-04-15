package com.example.mygame.ui.logic

import com.example.mygame.ui.engine.GameLogic
import com.example.mygame.ui.model.Pipe
import com.example.mygame.ui.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayerClashLogic (
    private val playerLogic: PlayerLogic,
    private val blockMovementLogic: BlockMovementLogic,
    private val gameStatusLogic: GameStatusLogic
): GameLogic {

    private val _clashHappened = MutableStateFlow(false)
    val clashHappened = _clashHappened.asStateFlow()
    override fun onUpdate(deltaTime: Float) {
            val player = playerLogic.player.value
            val blocks = blockMovementLogic.blockPosition.value
            val block = blocks.firstOrNull() ?: return

            val topPipeClash = clashed(player, block.topPipe)
            val bottomPipeClash = clashed(player, block.bottomPipe)
            val clashHappened = topPipeClash || bottomPipeClash
            if(clashHappened){
                gameStatusLogic.gameOver()
            }
            _clashHappened.value = clashHappened
    }
    private fun clashed (
        player: Player,
        pipe: Pipe
     ) = player.rect.overlaps(pipe.rect)
}