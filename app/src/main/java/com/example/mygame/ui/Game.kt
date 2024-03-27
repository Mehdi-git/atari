package com.example.mygame.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mygame.ui.components.Block
import com.example.mygame.ui.components.Player
import com.example.mygame.ui.engine.LogicManager
import com.example.mygame.ui.engine.gameCoroutineScope
import com.example.mygame.ui.logic.BlockLogic
import com.example.mygame.ui.logic.PlayerClashLogic
import com.example.mygame.ui.logic.PlayerLogic
import com.example.mygame.ui.engine.TimeManager
import com.example.mygame.ui.logic.GameScoreLogic
import com.example.mygame.ui.logic.GameStatusLogic
import com.example.mygame.ui.model.GameStatus

@Composable
fun Game(modifier: Modifier = Modifier) {



    val coroutineScope = remember {
        gameCoroutineScope()
    }

    val timeManager = remember {
        TimeManager()
    }
    val blockLogic = remember {
        BlockLogic()
    }
    val gameStatueLogic = remember {
        GameStatusLogic()
    }
    val playerLogic = remember {
        PlayerLogic(gameStatueLogic)
    }

    val playerClashLogic = remember {
        PlayerClashLogic(playerLogic, blockLogic)
    }
    val gameScoreLogic = remember {
        GameScoreLogic(playerLogic, blockLogic)
    }


    val logicManager = remember {
        val logics = listOf(
            playerLogic,
            blockLogic,
            playerClashLogic,
            gameScoreLogic,
            gameStatueLogic
        )
        LogicManager(logics, timeManager, coroutineScope,gameStatueLogic)
    }
    Box(modifier.clickable {
        playerLogic.jump()

    }){
        Player(playerLogic = playerLogic, playerClashLogic = playerClashLogic )
        Block(blockLogic)
        Text(text = gameScoreLogic.score.collectAsState().value.toString(), Modifier.align(Alignment.TopEnd))
    }




}


