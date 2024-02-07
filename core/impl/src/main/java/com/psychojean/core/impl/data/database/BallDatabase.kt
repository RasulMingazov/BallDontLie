package com.psychojean.core.impl.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.psychojean.core.impl.data.database.team.TeamDao
import com.psychojean.core.impl.data.database.team.model.TeamLocal

internal interface BallDatabase {

    fun teamDao(): TeamDao

    abstract class AbstractRoom(
        private val context: Context,
        private val databaseName: String
    ) : BallDatabase {

        @Database(entities = [TeamLocal::class], version = 1, exportSchema = false)
        abstract class ContentRoom : RoomDatabase() {
            abstract fun teamDao(): TeamDao
        }

        override fun teamDao(): TeamDao = database().teamDao()

        private fun database() = Room.databaseBuilder(
            context,
            ContentRoom::class.java,
            databaseName
        ).build()
    }

    class Ball(context: Context) : AbstractRoom(context, "ball_database")
}