package com.example.mygame.ui.logic

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mygame.ui.engine.GameLogic
import com.example.mygame.ui.model.Block
import com.example.mygame.ui.model.Viewport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class BlockMovementLogic(
    private val viewport: Viewport
) : GameLogic, OnGameOverLogic {
    private val _blockPosition = MutableStateFlow<List<Block>>(listOf())
    val blockPosition: StateFlow<List<Block>> = _blockPosition

    init {
        resetBlock()
    }
    override fun onUpdate(deltaTime: Float) {

        updateBlockX { x ->
             x - (deltaTime * scrollAmount).dp
        }

    }

    private fun updateBlockX(update: (Dp) -> Dp) {
        _blockPosition.update { blocks ->
            blocks.map { block ->
                block.copy(
                    topPipe = block.topPipe.copy(
                        x = update(block.topPipe.x)
                    ),
                    bottomPipe = block.bottomPipe.copy(
                        x = update(block.bottomPipe.x)
                    )
                )
            }
        }
    }

    fun scoreBlock(block: Block) {
        _blockPosition.update { blocks ->
            blocks.map {
               if (it.id !== block.id) return@map it
               it.copy(hasBeenScored = true)
           }
        }


    }
    private fun resetBlock() {
        _blockPosition.update { listOf() }
        updateBlockX{ _ -> viewport.width }
    }

    override fun onGameOver() {
        resetBlock()
    }

    fun addBlock(createBlock: Block) {
        _blockPosition.update {
            it.plus(createBlock)
        }
    }

    fun updateBlock(existingBlock: Block, updatedBlock: Block) {
        _blockPosition.update {
            blocks ->
            blocks.map {
                block ->
                if (block.id == existingBlock.id) {
                    return@map updatedBlock
                }
                block
            }
        }
    }

    companion object {
        const val scrollAmount = 0.2f
    }
}



