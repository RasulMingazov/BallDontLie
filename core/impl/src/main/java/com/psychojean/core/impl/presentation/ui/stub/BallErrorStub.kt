package com.psychojean.core.impl.presentation.ui.stub

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.psychojean.core.api.error.ErrorType

@Composable
fun BallErrorStub(
    modifier: Modifier = Modifier,
    errorType: ErrorType,
    isButtonLoading: Boolean = false,
    onButtonClick: (errorType: ErrorType) -> Unit = {}
) {
    BallStub(
        modifier = modifier,
        titleText = stringResource(errorType.title),
        descriptionText = stringResource(errorType.description),
        buttonText = stringResource(errorType.button),
        isButtonLoading = isButtonLoading,
        onButtonClick = { onButtonClick(errorType) }
    )
}