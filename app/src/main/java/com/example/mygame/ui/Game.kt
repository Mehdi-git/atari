package com.example.mygame.ui


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.mygame.ui.components.Block
import com.example.mygame.ui.components.Player
import com.example.mygame.ui.engine.gameCoroutineScope
import com.example.mygame.ui.logic.BlockLogic
import com.example.mygame.ui.logic.PlayerLogic
import com.example.mygame.ui.logic.TimeManager

@Composable
fun Game(modifier: Modifier = Modifier) {

    val coroutineScope = remember {
        gameCoroutineScope()
    }

    val timeManager = remember {
        TimeManager()
    }
    val playerLogic = remember {
        PlayerLogic(timeManager, coroutineScope)
    }
    val blockLogic = remember {
        BlockLogic(timeManager, coroutineScope)
    }

    Player(playerLogic)

    Block(blockLogic)

}


