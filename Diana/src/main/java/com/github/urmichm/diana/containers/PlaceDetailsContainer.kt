package com.github.urmichm.diana.containers


import androidx.lifecycle.Transformations
import com.google.android.libraries.places.api.model.AddressComponent
import com.google.android.libraries.places.api.model.AddressComponents
import com.google.android.libraries.places.api.model.Place
import com.squareup.moshi.Json

/**
 * @brief Container for Place object.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#Place
 * */
data class PlaceDetailsContainer(
    @Json(name="place_id") val placeId: String,
    val name :String,
    val rating :Double?,
    @Json(name="user_ratings_total") val userRatingsTotal :Int?,
    val vicinity :String,
    // TODO: map to PLace.Type
    val types :List<String>?,
    // TODO: geometry
    val geometry : GeometryContainer,
    @Json(name="icon") val iconUrl :String?
) {
    override fun toString(): String {
        return "${this.name} of type ${this.types} ${this.rating}"
    }

    // TODO: WTF is setAttributions()
    fun asPlace() : Place{
        return Place.builder()
//            .setAddressComponents(this.addressComponents)
            .setId(this.placeId)
            .setName(this.name)
            .setRating(this.rating)
            .setUserRatingsTotal(this.userRatingsTotal)
            .setAddress(this.vicinity)
//            .setTypes(this.types)
//            .setViewport(this.geometry.viewport)
//            .setLatLng(this.geometry.location)
            .setIconUrl(this.iconUrl)

            .build()
    }

}