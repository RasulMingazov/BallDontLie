package com.psychojean.feature.player.impl.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.psychojean.core.impl.presentation.effect.EventEffect
import com.psychojean.core.impl.presentation.error.ErrorType
import com.psychojean.core.impl.presentation.ui.bottom.BallBottomSheet
import com.psychojean.core.impl.presentation.ui.progress.BallProgress
import com.psychojean.core.impl.presentation.ui.stub.BallErrorStub
import com.psychojean.core.impl.presentation.ui.text.PairText
import com.psychojean.feature.player.impl.R
import com.psychojean.feature.player.impl.presentation.detail.model.PlayerModel
import com.psychojean.feature.player.impl.presentation.detail.model.PlayerTeamModel

@Composable
internal fun PlayerDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PlayerDetailViewModel = hiltViewModel<PlayerDetailViewModel>(),
    onBackPressed: () -> Unit
) {
    val playerState = viewModel.state.collectAsState()

    EventEffect(flow = viewModel.event) { playerDetailEvent ->
        when (playerDetailEvent) {
            is PlayerDetailEvent.Dismiss -> onBackPressed()
        }
    }
    BallBottomSheet(
        modifier = modifier,
        onDismissRequest = { viewModel.dismiss() }
    ) {
        when (val state = playerState.value) {
            is PlayerDetailState.Success -> Success(detail = state.player)
            is PlayerDetailState.Loading -> Progress()
            is PlayerDetailState.Reload -> Error(
                errorType = state.errorType,
                isButtonLoading = true
            )

            is PlayerDetailState.Error -> Error(
                errorType = state.errorType,
                isButtonLoading = false
            ) { viewModel.reload(it) }
        }
    }
}

@Composable
private fun Success(modifier: Modifier = Modifier, detail: PlayerModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = detail.fullName,
            style = MaterialTheme.typography.titleLarge
        )
        PairText(
            modifier = Modifier.padding(vertical = 8.dp),
            firstText = stringResource(id = R.string.position),
            secondText = detail.position
        )
        PairText(
            modifier = Modifier.padding(vertical = 8.dp),
            firstText = stringResource(id = R.string.team),
            secondText = detail.team.fullName
        )
        PairText(
            modifier = Modifier.padding(vertical = 8.dp),
            firstText = stringResource(id = R.string.height),
            secondText = detail.height
        )
        PairText(
            modifier = Modifier.padding(vertical = 8.dp),
            firstText = stringResource(id = R.string.weight),
            secondText = detail.weight
        )
    }
}

@Composable
private fun Progress(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BallProgress()
    }
}

@Composable
private fun Error(
    modifier: Modifier = Modifier,
    errorType: ErrorType,
    isButtonLoading: Boolean,
    onButtonClick: (errorType: ErrorType) -> Unit = {}
) {
    BallErrorStub(
        modifier = modifier,
        errorType = errorType,
        isButtonLoading = isButtonLoading,
        onButtonClick = onButtonClick
    )
}

@Composable
@Preview
private fun SuccessPreview() {
    Success(
        detail = PlayerModel(
            0, "Tyler Dorsey", "6 feet, 182.8 cm", "183 pounds, 83.0 kg", "G",
            PlayerTeamModel(7, "Dallas Mavericks(DAL)")
        )
    )
}

@Composable
@Preview
private fun ErrorPreview() {
    Error(errorType = ErrorType.Generic, isButtonLoading = false)
}

@Composable
@Preview
private fun ReloadPreview() {
    Error(errorType = ErrorType.Generic, isButtonLoading = true)
}


@Composable
@Preview
private fun ProgressPreview() {
    Progress()
}
