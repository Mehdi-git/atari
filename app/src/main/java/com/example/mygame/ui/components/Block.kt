package com.example.mygame.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.mygame.ui.logic.BlockLogic


@Composable
internal fun Block(blockLogic: BlockLogic, modifier: Modifier= Modifier) {
    Box(modifier) {
        val blockPositions = blockLogic.blockPosition.collectAsState().value
        val blockPosition = blockPositions.first()
        Pipe(blockPosition.topPipe)
        Pipe(blockPosition.bottomPipe)
    }
}