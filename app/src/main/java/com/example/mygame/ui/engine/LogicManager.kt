package com.example.mygame.ui.engine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LogicManager(
    gameLogics: List<GameLogic>,
    timeManager: TimeManager,
    coroutineScope: CoroutineScope,
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