package com.psychojean.core.impl.presentation.ui.footer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psychojean.core.impl.presentation.ui.progress.BallSmallProgress

@Composable
fun BallProgressFooter(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        BallSmallProgress()
    }
}

@Composable
@Preview
fun BallProgressFooterPreview() {
    BallProgressFooter()
}
