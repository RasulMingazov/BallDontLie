package com.psychojean.feature.connectivity.impl.presentation

internal data class ConnectivityState(
    val isConnected: Boolean = false,
    val isDisconnected: Boolean = false
) {

    fun copyToDisconnect() = copy(
        isDisconnected = true,
        isConnected = false
    )

    fun copyToConnect() = copy(
        isConnected = true,
        isDisconnected = false
    )

    fun reset() = copy(
        isConnected = false,
        isDisconnected = false
    )
}