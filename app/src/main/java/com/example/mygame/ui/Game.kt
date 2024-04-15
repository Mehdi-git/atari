package com.example.mygame.ui


import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mygame.ui.components.Background
import com.example.mygame.ui.components.Block
import com.example.mygame.ui.components.Player
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


@Composable
fun Game(modifier: Modifier = Modifier) {


    BoxWithConstraints {

    val viewport = remember{
        Viewport(maxWidth, maxHeight)
    }

    val coroutineScope = remember {
        gameCoroutineScope()
    }

    val timeManager = remember {
        TimeManager()
    }
    val blockMovementLogic = remember {
        BlockMovementLogic(viewport)
    }

    val blockCreatorLogic = remember {
        BlockCreatorLogic(blockMovementLogic, coroutineScope, viewport)
    }

    val gameStatueLogic = remember {
        GameStatusLogic()
    }
    val playerLogic = remember {
            PlayerLogic(gameStatueLogic)
    }
    val gameScoreLogic = remember {
        GameScoreLogic(playerLogic, blockMovementLogic)
    }

    val playerClashLogic = remember {
        PlayerClashLogic(playerLogic, blockMovementLogic, gameStatueLogic)
    }
    val gameOverManager = remember{
        val onGameOverLogic : List<OnGameOverLogic> = listOf(playerLogic, blockMovementLogic, gameScoreLogic)
        GameOverManager(onGameOverLogic,gameStatueLogic,coroutineScope)
    }


    val logicManager = remember {
        val logics = listOf(
            playerLogic,
            blockMovementLogic,
            playerClashLogic,
            gameScoreLogic,
            gameStatueLogic
        )
        LogicManager(logics, timeManager, coroutineScope,gameStatueLogic)
    }
        Box(
            modifier
                .fillMaxSize()
                .clickable {
                    playerLogic.jump()
                }) {

            Background(timeManager)
            Player(playerLogic = playerLogic, playerClashLogic = playerClashLogic)
            Block(blockMovementLogic)
            Text(
                text = gameScoreLogic.score.collectAsState().value.toString(),
                Modifier.align(Alignment.TopEnd)
            )
            Text(
                text = gameStatueLogic.gameState.collectAsState().value.toString(),
                Modifier.align((Alignment.TopStart))
            )
        }
    }

}


 fun Bitmap.resizeTo(maxHeight: Int): Bitmap {
    val sourceWidth = width
    val sourceHeight = height
    val sourceRatio = sourceWidth.toFloat() / sourceHeight.toFloat()
    val targetWidth = (maxHeight.toFloat() * sourceRatio).toInt()
    return Bitmap.createScaledBitmap(this, targetWidth, maxHeight, true)
}


