package com.example.mygame.ui.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.mygame.ui.engine.LogicManager
import com.example.mygame.ui.engine.TimeManager
import com.example.mygame.ui.engine.gameCoroutineScope
import com.example.mygame.ui.logic.BlockCreatorLogic
import com.example.mygame.ui.logic.BlockMovementLogic
import com.example.mygame.ui.logic.GameOverManager
import com.example.mygame.ui.logic.GameScoreLogic
import com.example.mygame.ui.logic.GameStatusLogic
import com.example.mygame.ui.logic.OnGameOverLogic
import com.example.mygame.ui.logic.PlayerClashLogic
import com.example.mygame.ui.logic.PlayerLogic
import com.example.mygame.ui.model.Viewport


class GameDI(viewport: Viewport) {

    val coroutineScope = gameCoroutineScope()
    val timeManager = TimeManager()
    val blockMovementLogic = BlockMovementLogic(viewport)
    val blockCreatorLogic = BlockCreatorLogic(blockMovementLogic, coroutineScope, viewport)
    val gameStatueLogic = GameStatusLogic()
    val playerLogic = PlayerLogic(gameStatueLogic)
    val gameScoreLogic = GameScoreLogic(playerLogic, blockMovementLogic)
    val playerClashLogic = PlayerClashLogic(playerLogic, blockMovementLogic, gameStatueLogic)
    val onGameOverLogic: List<OnGameOverLogic> =
        listOf(playerLogic, blockMovementLogic, gameScoreLogic)
    val gameOverManager = GameOverManager(onGameOverLogic, gameStatueLogic, coroutineScope)
    val logics = listOf(
        playerLogic,
        blockMovementLogic,
        playerClashLogic,
        gameScoreLogic,
        gameStatueLogic
    )
    val logicManager = LogicManager(logics, timeManager, coroutineScope, gameStatueLogic)

    companion object {

        @Composable
        fun rememberDi(viewport: Viewport): GameDI {
            return remember {
                GameDI(viewport)
            }
        }
    }
}

