package com.example.mygame.ui.logic

import com.example.mygame.ui.model.Block
import com.example.mygame.ui.model.Pipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlockLogic (private val timeManager : TimeManager) {

    private val _blockPosition = MutableStateFlow(Block(Pipe(0f,200f, 0f), Pipe(350f, 450f, 0f)))
    val blockPosition: StateFlow<Block> = _blockPosition

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        updateBlockX{ _ -> 400f }
        coroutineScope.launch {
            timeManager.deltaTime.collect{
                dt -> updateBlockX { x->
                    var newX = x - (dt * 0.1f)
                    if(newX < -100f) {
                        newX = 400f
                    }
                    newX
                }
            }
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



