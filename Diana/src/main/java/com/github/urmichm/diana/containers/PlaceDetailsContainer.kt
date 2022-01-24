package com.github.urmichm.diana.containers

import com.google.android.libraries.places.api.model.Place
import com.squareup.moshi.Json

/**
 * @brief Container for Place object.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#Place
 * */
data class PlaceDetailsContainer(
    @Json(name="place_id") val placeId: String,
    @Json(name="name") val name :String,
    @Json(name="price_level") val priceLevel : Int?,
    @Json(name="rating") val rating :Double?,
    @Json(name="user_ratings_total") val userRatingsTotal :Int?,
    @Json(name="vicinity") val vicinity :String,
    @Json(name="types") val types :List<String>?,
    @Json(name="geometry") val geometry : GeometryContainer,
    @Json(name="icon") val iconUrl :String?
) {
    override fun toString(): String {
        return "${this.name} of type ${this.types} ${this.rating}"
    }

    // TODO: WTF is setAttributions()
    fun asPlace() : Place{
        return Place.builder()
            .setId(this.placeId)
            .setName(this.name)
            .setPriceLevel(this.priceLevel)
            .setRating(this.rating)
            .setUserRatingsTotal(this.userRatingsTotal)
            .setAddress(this.vicinity)
            .setTypes(this.types?.map{ Place.Type.valueOf(it.uppercase()) })
            .setViewport(this.geometry.viewport.toLatLngBounds())
            .setLatLng(this.geometry.location.toLatLng())
            .setIconUrl(this.iconUrl)

            .build()
    }

}