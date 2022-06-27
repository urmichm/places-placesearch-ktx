package com.urmich.android.placesearchktx.containers

import com.urmich.android.placesearchktx.containers.common.PlaceDetailsContainer


abstract class PlaceSearchContainer {

    /**
     * May contain a set of attributions about this listing which must be displayed to the user
     * (some listings may not have attribution).
     * */
    abstract val htmlAttributions : List<String>?
    /**
     * Contains an array of places.
     * */
    abstract val places :List<PlaceDetailsContainer>
    /**
     * Contains the status of the request, and may contain debugging information to help you track down why the request failed.
     * */
    abstract val status : String
    /**
     * When the service returns a status code other than OK,
     * there may be an additional error_message field within the response object.
     * */
    abstract val errorMessage : String?
    /**
     * When the service returns additional information about the request specification,
     * there may be an additional info_messages field within the response object.
     * This field is only returned for successful requests. It may not always be returned, and its content is subject to change.
     * */
    abstract val infoMessages : List<String>?
    /**
     * Contains a token that can be used to return up to 20 additional results.
     * A next_page_token will not be returned if there are no additional results to display.
     * */
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