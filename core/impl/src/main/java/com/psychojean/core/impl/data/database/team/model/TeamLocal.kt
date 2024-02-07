package com.psychojean.core.impl.data.database.team.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamLocal(
    @PrimaryKey(autoGenerate = false)
    val teamId: Int,
    val name: String,
    val division: String,
    val abbreviation: String,
    val conference: String,
    val city: String,
    val isStarred: Boolean = false
)