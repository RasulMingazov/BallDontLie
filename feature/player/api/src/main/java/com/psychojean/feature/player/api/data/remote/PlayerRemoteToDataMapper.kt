package com.psychojean.feature.player.api.data.remote

import com.psychojean.core.api.Mapper
import com.psychojean.feature.player.api.data.model.player.PlayerData
import com.psychojean.feature.player.api.data.remote.model.PlayerRemote

interface PlayerRemoteToDataMapper: Mapper<PlayerRemote, PlayerData>