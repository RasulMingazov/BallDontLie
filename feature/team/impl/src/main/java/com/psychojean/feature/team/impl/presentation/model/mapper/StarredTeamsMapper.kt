package com.psychojean.feature.team.impl.presentation.model.mapper

import com.psychojean.core.api.Mapper
import com.psychojean.core.api.TextProvider
import com.psychojean.feature.team.impl.R
import javax.inject.Inject

interface StarredTeamsMapper : Mapper<Int, String>

internal class DefaultStarredTeamsMapper @Inject constructor(private val textProvider: TextProvider) : StarredTeamsMapper {

    override fun map(item: Int): String =
        if (item == 0) textProvider.getString(R.string.starred) else
            textProvider.getString(R.string.starred_value, item)
}