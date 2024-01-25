package com.psychojean.core.impl.presentation.save

import androidx.lifecycle.SavedStateHandle

interface SaveStateKey<T> {

    fun value(key: String): T

    fun save(key: String, value: T)

    abstract class BaseSaveStateKey<T>(private val savedStateHandle: SavedStateHandle): SaveStateKey<T> {

        override fun value(key: String): T = checkNotNull(savedStateHandle[key])

        override fun save(key: String, value: T) { savedStateHandle[key] = value }
    }
    
    class Digit(savedStateHandle: SavedStateHandle): BaseSaveStateKey<Int>(savedStateHandle)
}