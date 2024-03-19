package com.example.mygame.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.mygame.ui.model.Pipe


@Composable
internal fun Pipe(pipe: Pipe, modifier: Modifier = Modifier) {
    Box(
        modifier
            .offset {
                IntOffset(pipe.x.dp.roundToPx(), pipe.topY.dp.roundToPx())
            }
            .size(pipe.width.dp, (pipe.bottomY - pipe.topY).dp)
            .background(Color.Green)
    )
}
