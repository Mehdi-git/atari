package com.example.mygame.ui.engine

import com.example.mygame.ui.logic.GameScoreLogic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LogicManager(
    gameLogics: List<GameLogic>,
    timeManager: TimeManager,
    coroutineScope: CoroutineScope,
    gameScoreLogic: GameScoreLogic
) {

    init {
        coroutineScope.launch {
            timeManager.deltaTime.collect{
                deltaTime ->
                gameLogics.forEach { gameLogic -> gameLogic.onUpdate(deltaTime)  }

            }

        }
    }
}