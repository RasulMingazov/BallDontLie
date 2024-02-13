package com.psychojean.feature.connectivity.impl.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.psychojean.feature.team.impl.R

@Composable
fun ConnectivityIndicatorLayout(
    modifier: Modifier = Modifier,
    viewModel: ConnectivityViewModel = hiltViewModel()
) {

    val connectivityState by viewModel.state.collectAsStateWithLifecycle()

    ConnectivityIndicator(modifier = modifier, connectivityState = connectivityState)

}

@Composable
private fun ConnectivityIndicator(
    modifier: Modifier = Modifier,
    connectivityState: ConnectivityState
) {

    Box(modifier = modifier) {
        ConnectivityIndicatorCell(
            isVisible = connectivityState.isDisconnected,
            backgroundColor = MaterialTheme.colorScheme.error,
            textColor = MaterialTheme.colorScheme.onError,
            text = stringResource(id = R.string.disconnected)
        )
        ConnectivityIndicatorCell(
            isVisible = connectivityState.isConnected,
            backgroundColor = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.onPrimary,
            text = stringResource(id = R.string.connected)
        )
    }
}

@Composable
private fun ConnectivityIndicatorCell(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    backgroundColor: Color,
    textColor: Color,
    text: String
) {

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = modifier
                .background(backgroundColor)
                .padding(vertical = 4.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = text,
                color = textColor
            )
        }
    }
}

@Composable
@Preview
internal fun ConnectivityIndicatorPreview() {
    ConnectivityIndicator(connectivityState = ConnectivityState(isConnected = false))
}
