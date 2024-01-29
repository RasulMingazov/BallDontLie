package com.psychojean.core.impl.presentation.ui.stub

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.psychojean.core.impl.presentation.ui.progress.BallProgress

@Composable
fun BallProgressStub(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BallProgress()
    }
}