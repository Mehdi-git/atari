package com.example.mygame.ui

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.mygame.ui.components.Background
import com.example.mygame.ui.components.Block
import com.example.mygame.ui.components.Player
import com.example.mygame.ui.di.GameDI
import com.example.mygame.ui.model.Viewport

@Composable
fun Game(modifier: Modifier = Modifier) {

    BoxWithConstraints {

        val viewport = remember {
            Viewport(maxWidth, maxHeight)
        }
        val di = remember {
            GameDI(viewport)
        }

        Box(
            modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() }, indication = null
                ) {
                    di.playerLogic.jump()
                }) {

            Background(di.timeManager)
            Player(playerLogic = di.playerLogic, playerClashLogic = di.playerClashLogic)
            Block(di.blockMovementLogic)

            Text(
                text = di.gameScoreLogic.score.collectAsState().value.toString(),
                Modifier
                    .align(Alignment.TopEnd),
                color = Color.White,
                fontSize = 30.sp
            )
            Text(
                text = di.gameStatueLogic.gameState.collectAsState().value.toString(),
                Modifier.align((Alignment.TopStart)),
                color = Color.White,
                fontSize = 30.sp
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


