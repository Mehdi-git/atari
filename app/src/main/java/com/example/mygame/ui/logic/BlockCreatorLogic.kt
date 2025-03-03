package com.example.mygame.ui.logic

import com.example.mygame.ui.model.GameStatus
import com.example.mygame.ui.model.Viewport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import java.util.Random

class BlockCreatorLogic(
    private val blockMovementLogic: BlockMovementLogic,
    private val coroutineScope: CoroutineScope,
    private val viewport: Viewport,
    private val gameStatusLogic: GameStatusLogic
) {
    private val blockCreator = BlockCreator(viewport)

    init {
        coroutineScope.launch {
            gameStatusLogic.gameState.mapLatest { gameState ->
                if (gameState == GameStatus.Started) {
                    createBlock()
                }
            }.collect {

            }
        }
    }

    private suspend fun createBlock() {
        while (true) {
            val randomTime = 1800 + (Random().nextFloat() * 1000)
            delay(randomTime.toLong())
            val existingBlock = blockMovementLogic.blockPosition.value.firstOrNull {
                it.topPipe.x.value < -100
            }
            val createBlock = blockCreator.createBlock()
            if (existingBlock !== null) {
                val updatedBlock = existingBlock.copy(
                    hasBeenScored = false,
                    topPipe = createBlock.topPipe,
                    bottomPipe = createBlock.bottomPipe
                )
                blockMovementLogic.updateBlock(existingBlock, updatedBlock)
            } else {
                blockMovementLogic.addBlock(createBlock)

            }
        }
    }
}