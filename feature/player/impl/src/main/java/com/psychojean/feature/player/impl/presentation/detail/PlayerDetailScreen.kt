package com.psychojean.feature.player.impl.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import com.psychojean.core.impl.presentation.ui.bottom.ballBottomContentModifier
import com.psychojean.core.impl.presentation.ui.loading.BallProgress
import com.psychojean.core.impl.presentation.ui.stub.BallErrorStub
import com.psychojean.core.impl.presentation.ui.text.PairText
import com.psychojean.feature.player.api.domain.detail.model.PlayerDetailEntity
import com.psychojean.feature.player.impl.R

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
        contentModifier = modifier
            .ballBottomContentModifier()
            .padding(horizontal = 24.dp),
        onDismissRequest = { viewModel.dismiss() }
    ) {
        when (val state = playerState.value) {
            is PlayerDetailState.Success -> Success(detail = state.player)
            is PlayerDetailState.Loading -> Progress()
            is PlayerDetailState.Reload -> Error(
                errorType = state.errorType,
                isButtonLoading = true
            ) { viewModel.reload(it) }

            is PlayerDetailState.Error -> Error(
                errorType = state.errorType,
                isButtonLoading = false
            ) { viewModel.reload(it) }
        }
    }
}

@Composable
private fun Success(modifier: Modifier = Modifier, detail: PlayerDetailEntity) {
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        PairText(
            modifier = Modifier.padding(vertical = 8.dp),
            firstText = stringResource(id = R.string.id),
            secondText = detail.id.toString()
        )
        PairText(
            modifier = Modifier.padding(vertical = 8.dp),
            firstText = stringResource(id = R.string.first_name),
            secondText = detail.firstName
        )
        PairText(
            modifier = Modifier.padding(vertical = 8.dp),
            firstText = stringResource(id = R.string.last_name),
            secondText = detail.lastName
        )
    }
}

@Composable
private fun Progress(modifier: Modifier = Modifier) {
    BallProgress(modifier)
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
    Success(detail = PlayerDetailEntity(0, "Michael", "Jordan"))
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
