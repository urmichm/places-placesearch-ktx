package com.github.urmichm.diana.containers


/**
 * @brief Container fro nearBySearch request.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#PlacesNearbySearchResponse
 * */
data class PlacesNearbySearchContainer(
    val html_attributions : List<String>,
    val results :List<PlaceDetailsContainer>,
    val status : String,
    val error_message : String?,
    val info_messages : List<String>?,
    val next_page_token : String?
) {

    override fun toString(): String {
        var str = "status: $status"
        if(error_message != null) {
            str += " $error_message"
        }
        return str
    }
}

