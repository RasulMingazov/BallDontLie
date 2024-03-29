package com.psychojean.feature.player.impl.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.psychojean.core.impl.presentation.ui.bottom.BallBottomSheet
import com.psychojean.core.impl.presentation.ui.stub.BallErrorStub
import com.psychojean.core.impl.presentation.ui.stub.BallProgressStub
import com.psychojean.core.impl.presentation.ui.text.PairText
import com.psychojean.feature.player.impl.R
import com.psychojean.feature.player.impl.presentation.model.PlayerModel

@Composable
internal fun PlayerScreen(
    modifier: Modifier = Modifier,
    viewModel: PlayerViewModel = hiltViewModel<PlayerViewModel>(),
    onBackPressed: () -> Unit
) {
    val playerState by viewModel.state.collectAsStateWithLifecycle()

    BallBottomSheet(
        modifier = modifier,
        onDismissRequest = onBackPressed
    ) { PlayerContent(playerState, viewModel) }
}

@Composable
private fun PlayerContent(
    playerState: PlayerUiState,
    viewModel: PlayerViewModel
) {
    when {
        playerState.player != null -> PlayerDetail(player = playerState.player)
        playerState.isLoading -> BallProgressStub()
        playerState.error != null -> BallErrorStub(
            errorType = playerState.error,
            isButtonLoading = playerState.isRetry,
            onButtonClick = viewModel::retry
        )
    }
}


@Composable
internal fun PlayerDetail(modifier: Modifier = Modifier, player: PlayerModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = player.fullName,
            style = MaterialTheme.typography.titleLarge
        )
        PairText(
            modifier = Modifier.padding(vertical = 8.dp),
            firstText = stringResource(id = R.string.position),
            secondText = player.position
        )
        PairText(
            modifier = Modifier.padding(vertical = 8.dp),
            firstText = stringResource(id = R.string.team),
            secondText = player.teamName
        )
        PairText(
            modifier = Modifier.padding(vertical = 8.dp),
            firstText = stringResource(id = R.string.height),
            secondText = player.height
        )
        PairText(
            modifier = Modifier.padding(vertical = 8.dp),
            firstText = stringResource(id = R.string.weight),
            secondText = player.weight
        )
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun PlayerPreview() {
    PlayerDetail(
        player = PlayerModel(
            0,
            "Tyler Dorsey",
            "6 feet, 182.8 cm",
            "183 pounds, 83.0 kg",
            "G",
            0,
            "Chicago"
        )
    )
}
