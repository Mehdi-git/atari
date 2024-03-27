package com.example.mygame.ui.engine

import com.example.mygame.ui.logic.GameScoreLogic
import com.example.mygame.ui.logic.GameStatusLogic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LogicManager(
    gameLogics: List<GameLogic>,
    timeManager: TimeManager,
    coroutineScope: CoroutineScope,
    gameStatusLogic: GameStatusLogic
) {

    init {
        coroutineScope.launch {
            timeManager.deltaTime.collect{ deltaTime ->
                if(!gameStatusLogic.isStarted()) return@collect
                gameLogics.forEach { gameLogic -> gameLogic.onUpdate(deltaTime)  }

            }

        }
    }
}