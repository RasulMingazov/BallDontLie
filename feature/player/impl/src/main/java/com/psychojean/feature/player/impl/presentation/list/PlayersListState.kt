package com.psychojean.feature.player.impl.presentation.list

import com.psychojean.core.impl.presentation.error.ErrorType

internal sealed class PlayersListState {
    data object Loading : PlayersListState()
    data object NotLoading : PlayersListState()
    data class Error(val errorType: ErrorType) : PlayersListState()
}

internal sealed class PlayersListAppendState {
    data object NotLoading: PlayersListAppendState()
    data object Loading : PlayersListAppendState()
    data class Error(val errorType: ErrorType) : PlayersListAppendState()
}
