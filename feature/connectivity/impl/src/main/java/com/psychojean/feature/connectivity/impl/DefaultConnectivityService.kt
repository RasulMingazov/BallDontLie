package com.psychojean.feature.connectivity.impl

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.psychojean.feature.connectivity.api.ConnectivityService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

internal class DefaultConnectivityService @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val networkCallback: NetworkCallback
) : ConnectivityService {

    override fun isConnected(): Boolean {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    override fun observeConnectivity() = callbackFlow {
        networkCallback.init(this)
        networkCallback.registerToConnectivityManager(connectivityManager)
        awaitClose { networkCallback.unregisterFromConnectivityManager(connectivityManager) }
    }.distinctUntilChanged()
}