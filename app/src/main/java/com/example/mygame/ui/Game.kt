package com.example.mygame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.mygame.ui.components.Player
import com.example.mygame.ui.logic.BlockLogic
import com.example.mygame.ui.logic.PlayerLogic
import com.example.mygame.ui.logic.TimeManager
import com.example.mygame.ui.model.Pipe

@Composable
fun Game(modifier: Modifier = Modifier) {

    val timeManager = remember {
        TimeManager()
    }
    val playerLogic = remember {
        PlayerLogic(timeManager)
    }
    val blockLogic = remember {
        BlockLogic(timeManager)
    }

    Player(playerLogic)

    Box {
        val blockPosition = blockLogic.blockPosition.collectAsState().value
        Pipe(blockPosition.topPipe)
        Pipe(blockPosition.bottomPipe)
    }


}

@Composable
private fun Pipe(pipe: Pipe) {
    Box(
        modifier = Modifier
            .offset {
                IntOffset(pipe.x.dp.roundToPx(), pipe.topY.dp.roundToPx())
            }
            .size(20.dp, (pipe.bottomY - pipe.topY).dp)
            .background(Color.Blue)
    )
}

