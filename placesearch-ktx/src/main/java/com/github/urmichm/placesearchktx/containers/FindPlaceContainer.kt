package com.github.urmichm.placesearchktx.containers

import com.github.urmichm.placesearchktx.containers.common.PlaceDetailsContainer
import com.squareup.moshi.Json

/**
 * Container for findPlace request.
 * Place Search requests return a subset of the fields that are returned by Place Details requests.
 * If the field you want is not returned by Place Search, you can use Place Search to get a place_id,
 * then use that Place ID to make a Place Details request.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place
 * */
class FindPlaceContainer (

    /**
     * Contains an array of Place candidates.
     */
    @Json(name="candidates")
    override val places :List<PlaceDetailsContainer>,

    /**
     * Contains the status of the request, and may contain debugging information to help you track down why the request failed.
     */
    @Json(name="status")
    override val status : String,

    /**
     * When the service returns a status code other than OK<, there may be an additional error_message field within the response object.
     * This field contains more detailed information about thereasons behind the given status code.
     * This field is not always returned, and its content is subject to change.
     */
    @Json(name="error_message")
    override val errorMessage : String?,

    /**
     * When the service returns additional information about the request specification,
     * there may be an additional info_messages field within the response object.
     * This field is only returned for successful requests. It may not always be returned,
     * and its content is subject to change.
     */
    @Json(name="info_messages")
    override val infoMessages : List<String>?,

    @Json(name="html_attributions")
    override val htmlAttributions: List<String>?,
    @Json(name="next_page_token")
    override val nextPageToken: String?
): PlaceSearchContainer() {

    override fun toString(): String =  super.toString()

}