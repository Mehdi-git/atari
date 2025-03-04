package com.example.mygame.ui.logic

import androidx.compose.ui.unit.dp
import com.example.mygame.ui.model.Block
import com.example.mygame.ui.model.Pipe
import com.example.mygame.ui.model.Viewport
import java.util.Random

class BlockCreator(private val viewport: Viewport){

    private val minimumHeightPercentage = 0.1f
    private val minimumGateHeightPercentage = 0.40f
    private val maximumGateHeightPercentage = 0.45f

    fun createBlock(): Block {
        val totalHeight = viewport.height
        val maximumGateHeight = totalHeight * maximumGateHeightPercentage
        val usableHeight =
            (totalHeight * (1f - minimumHeightPercentage)).coerceAtMost(maximumGateHeight)
        val minimumHeight = usableHeight * minimumGateHeightPercentage
        val pipeMinimumHeight = viewport.height * (minimumHeightPercentage / 2f)

        val randomFloat = Random().nextFloat()
        val gateHeight = minimumHeight + (usableHeight - minimumHeight) * randomFloat

        val minimumGateStartY = pipeMinimumHeight
        val maximumGateEndY = totalHeight * (1f - minimumHeightPercentage) - gateHeight

        val gateStart = minimumGateStartY + maximumGateEndY * (Random().nextFloat())

        return Block(
            Pipe(0.dp, gateStart, viewport.width),
            Pipe(gateStart + gateHeight, totalHeight, viewport.width)
        )
    }
}