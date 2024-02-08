package com.psychojean.core.api

interface TextProvider {

    fun getString(resource: Int, second: Int): String

    fun getString(resource: Int): String

}