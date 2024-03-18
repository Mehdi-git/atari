package com.example.mygame.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import com.example.mygame.ui.logic.PlayerLogic
import com.example.mygame.ui.model.ToDpSize

@Composable
internal fun Player(playerLogic: PlayerLogic) {
    Box(Modifier.clickable {
        playerLogic.jump()
    }) {
        val player = playerLogic.player.collectAsState()
        Box(
            Modifier
                .offset {
                    IntOffset(x = 0, y = player.value.y.toInt())
                }
                .size(player.value.size.ToDpSize())
                .background(Color.Blue)
        )
    }
}