package com.psychojean.core.impl.presentation.ui.bottom

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.psychojean.core.impl.presentation.ui.screenHeight

@Composable
fun minBottomHeight() = screenHeight() / 3

fun Modifier.ballBottomContentModifier() = composed {
    fillMaxWidth().defaultMinSize(minHeight = minBottomHeight()) }