package com.psychojean.core.impl.data.database.team

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.psychojean.core.impl.data.database.team.model.TeamLocal

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(team: TeamLocal)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(teams: List<TeamLocal>)

    @Query("UPDATE TeamLocal SET isStarred = :isStarred WHERE teamId =:teamId")
    suspend fun star(teamId: Int, isStarred: Boolean)

    @Query("SELECT * FROM TeamLocal")
    fun teams(): List<TeamLocal>

    @Query("SELECT COUNT(*) FROM TeamLocal")
    fun teamsCount(): Int

    @Query("SELECT * FROM TeamLocal WHERE isStarred =:isStarred")
    fun starredTeams(isStarred: Boolean = true): List<TeamLocal>

    @Query("DELETE FROM TeamLocal")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM TeamLocal WHERE isStarred =:isStarred")
    fun starredTeamsCount(isStarred: Boolean = true): Int
}