package com.psychojean.feature.connectivity.impl

import android.content.Context
import android.net.ConnectivityManager
import com.psychojean.feature.connectivity.api.ConnectivityService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
internal interface ConnectivityModule {

    @Binds
    fun bindConnectivityService(
        connectivityService: DefaultConnectivityService
    ): ConnectivityService

    @Binds
    fun bindNetworkCallback(
        networkCallback: DefaultBaseNetworkCallback
    ): NetworkCallback

    companion object {

        @Provides
        fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}
