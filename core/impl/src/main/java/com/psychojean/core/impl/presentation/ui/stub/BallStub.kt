package com.psychojean.core.impl.presentation.ui.stub

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psychojean.core.impl.presentation.ui.button.BallProgressButton

@Composable
fun BallStub(
    modifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    descriptionModifier: Modifier = Modifier,
    primaryButtonModifier: Modifier = Modifier,
    titleText: String,
    descriptionText: String,
    buttonText: String,
    isButtonLoading: Boolean = false,
    onButtonClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = titleModifier,
            text = titleText,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = descriptionModifier.padding(top = 8.dp),
            text = descriptionText,
            style = MaterialTheme.typography.bodyMedium
        )
        BallProgressButton(
            modifier = primaryButtonModifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp),
            buttonText = buttonText,
            isButtonLoading = isButtonLoading,
            onButtonClick = onButtonClick
        )
    }
}

@Composable
@Preview
private fun BallStubPreview() {
    BallStub(
        modifier = Modifier,
        titleText = "Title",
        descriptionText = "Description",
        buttonText = "Reload",
        isButtonLoading = false
    )
}