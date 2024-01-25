package com.psychojean.core.impl.presentation.ui.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psychojean.core.impl.presentation.ui.loading.BallProgressInButton

@Composable
fun BallProgressButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    isButtonLoading: Boolean,
    onButtonClick: () -> Unit = {}
) {
    val isLoading = remember { mutableStateOf(isButtonLoading) }

    Button(
        modifier = modifier,
        onClick = { if (!isLoading.value) onButtonClick() }
    ) {
        if (isLoading.value)
            BallProgressInButton(
                Modifier
                    .padding(horizontal = 24.dp)
                    .size(16.dp))
        else
            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = buttonText,
                fontSize = 16.sp
            )
    }
}

@Preview
@Composable
private fun BallProgressButtonPreview() {
    BallProgressButton(buttonText = "Preview", isButtonLoading = false)
}