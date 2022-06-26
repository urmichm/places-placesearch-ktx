package com.urmich.android.placesearchktx.containers.search

import com.urmich.android.placesearchktx.containers.PlaceSearchContainer
import com.urmich.android.placesearchktx.containers.common.PlaceDetailsContainer
import com.squareup.moshi.Json

/**
 * Container for findPlace request.
 * Place Search requests return a subset of the fields that are returned by Place Details requests.
 * If the field you want is not returned by Place Search, you can use Place Search to get a place_id,
 * then use that Place ID to make a Place Details request.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place
 * */
class FindPlaceContainer (
    @Json(name="candidates")
    override val places :List<PlaceDetailsContainer>,
    @Json(name="status")
    override val status : String,
    @Json(name="error_message")
    override val errorMessage : String?,
    @Json(name="info_messages")
    override val infoMessages : List<String>?,

    @Json(name="html_attributions")
    override val htmlAttributions: List<String>?,
    @Json(name="next_page_token")
    override val nextPageToken: String?
): PlaceSearchContainer() {

    override fun toString(): String =  super.toString()

}