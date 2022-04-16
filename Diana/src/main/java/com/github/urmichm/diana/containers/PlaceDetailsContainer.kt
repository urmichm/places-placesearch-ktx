package com.github.urmichm.diana.containers

import android.os.Build
import androidx.annotation.RequiresApi
import com.github.urmichm.diana.Diana
import com.google.android.libraries.places.api.model.Place
import com.squareup.moshi.Json

/**
 * Container for a Place object.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#Place
 *
 * @warning https://developers.google.com/maps/documentation/places/web-service/place-data-fields#places-api-fields-support
 * */
data class PlaceDetailsContainer(
    @Json(name="place_id") val placeId: String,
    @Json(name="name") val name :String,
    @Json(name="business_status") val businessStatus : String?,
    @Json(name="price_level") val priceLevel : Int?,
    @Json(name="rating") val rating :Double?,
    @Json(name="user_ratings_total") val userRatingsTotal :Int?,
    @Json(name="vicinity") val vicinity :String,
    @Json(name="types") val types :List<String>?,
    @Json(name="geometry") val geometry : GeometryContainer,
    @Json(name="photos") val photos : List<PhotoMetadataContainer>?,
    @Json(name="opening_hours") var openingHours : OpeningHoursContainer?,
    @Json(name="plus_code") val plusCode : PlusCodeContainer?,

    @Json(name="icon") val iconUrl :String?,
    @Json(name="icon_background_color") val iconBackgroundColor :String?,
    @Json(name="icon_mask_base_uri") val iconMaskBaseUri :String?,

    @Deprecated(message="Deprecated. The reference response field has been replaced by the place_id response, and should not be used.", replaceWith = ReplaceWith("place_id"))
    @Json(name="reference") val reference : String?,
    @Deprecated(message="Deprecated. The scope fields have no equivalent, as they were only used for the Place Add service which was turned off in July 2018.")
    @Json(name="scope") val scope : String?
) {

    override fun toString(): String {
        return "${this.name}[${this.rating}]"
    }

    /**
     * Returns boolean if the place is open at the moment of the API call.
     * @return true if the place is open at the moment of the call, false otherwise
     * */
    fun isOpen():Boolean?{
        return this.openingHours?.openNow;
    }

    /**
     * Converts [PlaceDetailsContainer] into [Place] object
     * @warning Requires API [Build.VERSION_CODES.N]
     * */
    @RequiresApi(Build.VERSION_CODES.N)
    fun asPlace() : Place{
        val placeBuilder =  Place.builder()
            .setBusinessStatus(this.businessStatus?.uppercase()?.let { Place.BusinessStatus.valueOf(it) })
            .setId(this.placeId)
            .setName(this.name)
            .setPriceLevel(this.priceLevel)
            .setRating(this.rating)
            .setUserRatingsTotal(this.userRatingsTotal)
            .setTypes(this.types?.map{ Place.Type.valueOf(it.uppercase()) })
            .setViewport(this.geometry.viewport.toLatLngBounds())
            .setLatLng(this.geometry.location.toLatLng())
            .setIconBackgroundColor(this.iconBackgroundColor?.substring(1)?.toInt(16))
            .setIconUrl(this.iconUrl)
//          .setPhotoMetadatas(this.photos?.stream()?.map { it.toPhotoMetadata() }?.toList())
            .setPlusCode(this.plusCode?.toPlusCode())

/*  The Following fields are not returned by Place Search, Nearby Search, and Text Search */
//            .setAddress("")
//            .setAddressComponents()
//            .setAttributions()
//            .setOpeningHours(@Nullable OpeningHours var1);
//            .setPhoneNumber(@Nullable String var1);
//            .setUtcOffsetMinutes(@Nullable Integer var1);
//            .setWebsiteUri(@Nullable Uri var1);

        if(null == placeBuilder.address && Diana.vicinityAsAddress) {
            placeBuilder.address = this.vicinity
        }

        return placeBuilder.build()
    }

}