package com.example.mygame.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.mygame.R
import com.example.mygame.ui.logic.PlayerClashLogic
import com.example.mygame.ui.logic.PlayerLogic
import com.example.mygame.ui.model.toDpSize

@Composable
internal fun Player(
    modifier: Modifier = Modifier,
    playerLogic: PlayerLogic,
    playerClashLogic: PlayerClashLogic
) {
    Box(modifier) {
        val player = playerLogic.player.collectAsState()
        val clash = playerClashLogic.clashHappened.collectAsState().value
        Image(
            painterResource(id = R.drawable.car_one),
            contentDescription = null,
            Modifier
                .offset {
                    IntOffset(x = 0, y = player.value.y.dp.roundToPx())
                }
                .size(player.value.size.toDpSize())
        )
    }
}