package com.psychojean.core.impl.presentation.ui.progress

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BallProgress(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier)
}

@Composable
fun BallProgressInButton(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        strokeWidth = 2.dp,
        trackColor = Color.White
    )
}


