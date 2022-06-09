package com.github.urmichm.placesearchktx.containers

import com.github.urmichm.placesearchktx.containers.common.PlaceDetailsContainer


abstract class PlaceSearchContainer {

    abstract val htmlAttributions : List<String>
    abstract val places :List<PlaceDetailsContainer>
    abstract val status : String
    abstract val errorMessage : String?
    abstract val infoMessages : List<String>?
    abstract val nextPageToken : String?


    override fun toString(): String {
        val str = StringBuilder()
        str.appendLine("status: $status")
        errorMessage?.let{
            str.appendLine( "Error: $it" )
        }
        infoMessages?.forEach {
            str.appendLine(it)
        }
        places.let {
            str.appendLine("Places found: ${it.size}")
        }
        return str.toString()
    }
}