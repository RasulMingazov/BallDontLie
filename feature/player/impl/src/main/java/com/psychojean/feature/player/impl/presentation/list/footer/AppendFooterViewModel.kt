package com.psychojean.feature.player.impl.presentation.list.footer

import androidx.lifecycle.ViewModel
import com.psychojean.core.api.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.di.ErrorQualifier
import com.psychojean.core.impl.presentation.paging.PagingAppendListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class AppendFooterViewModel @Inject constructor(
    @ErrorQualifier private val errorTypeMapper: ErrorTypeMapper
) : ViewModel(), PagingAppendListener {

    private val _state = MutableStateFlow(AppendFooterUiState())
    val state = _state.asStateFlow()

    override fun appendLoadingStarted() {
        _state.update { it.copy(isLoading = true, error = null) }
    }

    override fun appendLoadingFinished() {
        _state.update { it.copy(isLoading = false) }
    }

    override fun appendLoadingError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = errorTypeMapper.map(throwable)) }
    }

}