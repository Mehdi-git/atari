package com.example.mygame.ui.logic

import com.example.mygame.ui.model.GameStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class GameOverManager(
    onGameOverLogic: List<OnGameOverLogic>,
    gameStatusLogic: GameStatusLogic,
    private val coroutineScope: CoroutineScope,

) {

    init {
        coroutineScope.launch {
            gameStatusLogic.gameState.filter { gameStatus ->
                gameStatus == GameStatus.GameOver
            }.collect{
                onGameOverLogic.forEach { it.onGameOver() }
            }
        }
    }
}