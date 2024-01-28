package com.psychojean.core.api

interface TextProvider {

    fun getString(resource: Int, second: String): String

}