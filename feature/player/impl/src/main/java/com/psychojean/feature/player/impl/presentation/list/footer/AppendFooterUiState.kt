package com.psychojean.feature.player.impl.presentation.list.footer

import com.psychojean.core.impl.presentation.error.ErrorType

internal data class AppendFooterUiState(
    val isLoading: Boolean = false,
    val error: ErrorType? = null
)