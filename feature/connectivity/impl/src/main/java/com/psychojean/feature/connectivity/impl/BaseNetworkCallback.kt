package com.psychojean.feature.connectivity.impl

import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.launch
import javax.inject.Inject

internal interface NetworkCallback {

    fun init(scope: ProducerScope<Boolean>)

    fun registerToConnectivityManager(connectivityManager: ConnectivityManager)

    fun unregisterFromConnectivityManager(connectivityManager: ConnectivityManager)

}

internal abstract class BaseNetworkCallback : NetworkCallback, ConnectivityManager.NetworkCallback() {

    override fun registerToConnectivityManager(connectivityManager: ConnectivityManager) {
        connectivityManager.registerDefaultNetworkCallback(this)
    }

    override fun unregisterFromConnectivityManager(connectivityManager: ConnectivityManager) {
        connectivityManager.unregisterNetworkCallback(this)
    }

}

internal class DefaultBaseNetworkCallback @Inject constructor() : BaseNetworkCallback() {

    private lateinit var scope: ProducerScope<Boolean>

    override fun init(scope: ProducerScope<Boolean>) {
        this.scope = scope
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        scope.launch { scope.send(true) }
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        scope.launch { scope.send(false) }
    }
}
