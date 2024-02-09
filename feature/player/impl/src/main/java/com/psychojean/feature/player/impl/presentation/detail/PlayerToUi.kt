package com.psychojean.feature.player.impl.presentation.detail

import com.psychojean.core.api.ResultToUi
import com.psychojean.feature.player.impl.domain.detail.PlayerResult
import com.psychojean.feature.player.impl.presentation.model.PlayerEntityToModelMapper
import javax.inject.Inject

internal interface PlayerToUi : ResultToUi<PlayerUiState, PlayerResult>

internal class DefaultPlayerToUi @Inject constructor(
    private val playerDetailEntityToModelMapper: PlayerEntityToModelMapper,
) : PlayerToUi {

    override fun PlayerUiState.copyFromResult(result: PlayerResult): PlayerUiState =
        when (result) {
            is PlayerResult.Error -> copyToError(result.errorType)
            is PlayerResult.Success -> copyToPlayer(
                playerDetailEntityToModelMapper.map(result.player)
            )
        }
}
