package com.psychojean.core.impl.presentation.ui.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.psychojean.core.impl.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BallTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onNavigationIconClick: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            if (onNavigationIconClick != null)
                Icon(
                    modifier = Modifier.clickable { onNavigationIconClick() },
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.back)
                )
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center
            )
        }
    )
}

@Preview
@Composable
private fun BallTopBarPreview() {
    BallTopBar(title = "Title")
}