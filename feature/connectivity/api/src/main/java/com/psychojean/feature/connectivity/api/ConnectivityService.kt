package com.psychojean.feature.connectivity.api

import kotlinx.coroutines.flow.Flow

interface ConnectivityService {

    fun observeConnectivity(): Flow<Boolean>

    fun isConnected(): Boolean
}
