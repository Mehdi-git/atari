package com.example.mygame.ui.logic

import com.example.mygame.ui.engine.GameLogic
import com.example.mygame.ui.model.GameStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameStatusLogic (
) : GameLogic {

    private val _gameState = MutableStateFlow(GameStatus.NotStarted)
    val gameState = _gameState.asStateFlow()

    override fun onUpdate(deltaTime: Float) {


    }
    fun gameStarted(){
        _gameState.update {
            GameStatus.Started
        }
    }

    fun gameOver(){
        _gameState.update{
            GameStatus.GameOver
        }
    }

    fun isStarted(): Boolean {
        return _gameState.value == GameStatus.Started

    }
}