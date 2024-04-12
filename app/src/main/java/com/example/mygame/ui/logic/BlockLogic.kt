package com.example.mygame.ui.logic

import androidx.compose.ui.unit.dp
import com.example.mygame.ui.engine.GameLogic
import com.example.mygame.ui.model.Block
import com.example.mygame.ui.model.Pipe
import com.example.mygame.ui.model.Viewport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class BlockLogic(
    private val viewport: Viewport
) : GameLogic, OnGameOverLogic {

    private val defaultBlock = Block(
        Pipe(0f, 200f, 0f),
        Pipe(300f, viewport.height, 0f))

    private val _blockPosition = MutableStateFlow(defaultBlock)
    val blockPosition: StateFlow<Block> = _blockPosition

    init {
        resetBlock()
    }
    override fun onUpdate(deltaTime: Float) {
        updateBlockX { x ->
            var newX = x - (deltaTime * 0.1f)
            if (newX < -100f) {
                newX = 400f
            }
            newX
        }
    }

    private fun updateBlockX(update: (Float) -> Float) {
        _blockPosition.update { block ->
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

    fun scoreBlock(block: Block) {
        _blockPosition.update {
            it.copy(hasBeenScored = true)
        }


    }
    private fun resetBlock() {
        _blockPosition.update { defaultBlock }
        updateBlockX{ _ -> 400f }
    }

    override fun onGameOver() {
        resetBlock()    }
}



