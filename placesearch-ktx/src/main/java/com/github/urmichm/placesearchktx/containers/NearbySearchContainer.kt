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
    override val htmlAttributions : List<String>,
    @Json(name="results")
    override val places :List<PlaceDetailsContainer>,
    @Json(name="status")
    override val status : String,
    @Json(name="error_message")
    override val errorMessage : String?,
    @Json(name="info_messages")
    override val infoMessages : List<String>?,
    @Json(name="next_page_token")
    override val nextPageToken : String?
) : PlaceSearchContainer() {

    override fun toString(): String =  super.toString()

}

