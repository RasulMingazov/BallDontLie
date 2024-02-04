package com.psychojean.feature.team.api.data.remote

import com.psychojean.core.api.Mapper
import com.psychojean.feature.team.api.data.model.TeamData
import com.psychojean.feature.team.api.data.remote.model.TeamRemote

interface TeamRemoteToDataMapper: Mapper<TeamRemote, TeamData>