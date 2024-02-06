package com.psychojean.feature.player.impl.presentation.list.footer

import com.psychojean.core.api.error.ErrorType

internal data class AppendFooterUiState(
    val isLoading: Boolean = false,
    val error: ErrorType? = null
)