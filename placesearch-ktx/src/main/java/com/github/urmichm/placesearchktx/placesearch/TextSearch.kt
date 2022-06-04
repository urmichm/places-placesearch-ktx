package com.github.urmichm.placesearchktx.placesearch

import com.github.urmichm.placesearchktx.Diana
import com.github.urmichm.placesearchktx.containers.TextSearchContainer

/**
 * Text Search Google API
 * @details https://developers.google.com/maps/documentation/places/web-service/search-text
 * @param builder The [Builder] object
 * */
class TextSearch private constructor(private val builder: Builder){
    /**
     * [Diana] object with general settings
     * */
    private val diana : Diana = builder.diana


    class Builder(val diana : Diana){


        /**
         * The build method to create a [TextSearch] object
         * @return [TextSearch] object created according to the builder settings.
         * */
        fun build() :TextSearch{
            return TextSearch(this)
        }
    }

    /**
     * Make a call to Text Search.
     * @return [TextSearchContainer] container object on success, null otherwise
     * */
    suspend fun call() : TextSearchContainer?{
        TODO("Not yet implemented")
    }
}