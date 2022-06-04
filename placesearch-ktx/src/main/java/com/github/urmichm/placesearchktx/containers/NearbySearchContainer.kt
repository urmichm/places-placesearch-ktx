package com.github.urmichm.placesearchktx.containers

import com.github.urmichm.placesearchktx.containers.common.PlaceDetailsContainer
import com.squareup.moshi.Json


/**
 * Container fro nearBySearch request.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#PlacesNearbySearchResponse
 * https://developers.google.com/maps/documentation/places/web-service/search-nearby#PlacesSearchStatus
 * */
data class NearbySearchContainer(
    @Json(name="html_attributions")
    val htmlAttributions : List<String>,
    @Json(name="results")
    val results :List<PlaceDetailsContainer>,
    @Json(name="status")
    val status : String,
    @Json(name="error_message")
    val errorMessage : String?,
    @Json(name="info_messages")
    val infoMessages : List<String>?,
    @Json(name="next_page_token")
    val nextPageToken : String?
) {

    override fun toString(): String {
        var str = "status: $status"
        if(errorMessage != null) {
            str += " $errorMessage"
        }
        return str
    }
}

