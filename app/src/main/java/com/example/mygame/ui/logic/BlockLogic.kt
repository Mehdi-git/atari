package com.example.mygame.ui.logic

import com.example.mygame.ui.engine.GameLogic
import com.example.mygame.ui.model.Block
import com.example.mygame.ui.model.Pipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class BlockLogic: GameLogic {

    private val _blockPosition = MutableStateFlow(Block(Pipe(0f,200f, 0f), Pipe(300f, 450f, 0f)))
    val blockPosition: StateFlow<Block> = _blockPosition

    init {
        updateBlockX{ _ -> 400f }

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
}



