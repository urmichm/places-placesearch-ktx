package com.github.urmichm.diana.containers

import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.squareup.moshi.Json


/**
 * @brief Container fro nearBySearch request.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#PlacesNearbySearchResponse
 * */
data class PlacesNearbySearchContainer(
    @Json(name="html_attributions") val htmlAttributions : List<String>,

    val results :List<PlaceDetailsContainer>,

    // TODO: https://developers.google.com/maps/documentation/places/web-service/search-nearby#PlacesSearchStatus
    val status : String,

    @Json(name="error_message") val errorMessage : String?,
    @Json(name="info_messages") val infoMessages : List<String>?,
    @Json(name="next_page_token") val nextPageToken : String?
) {

    override fun toString(): String {
        var str = "status: $status"
        if(errorMessage != null) {
            str += " $errorMessage"
        }
        return str
    }
}

