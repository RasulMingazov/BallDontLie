package com.psychojean.core.impl.core

import android.content.Context
import androidx.annotation.StringRes
import com.psychojean.core.api.TextProvider

class DefaultTextProvider(private val context: Context) : TextProvider {

    override fun getString(@StringRes resource: Int, second: Int) = context.getString(resource, second)

    override fun getString(resource: Int): String = context.getString(resource)

}