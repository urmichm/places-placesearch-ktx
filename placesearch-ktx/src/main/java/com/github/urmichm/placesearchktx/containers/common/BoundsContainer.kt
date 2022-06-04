package com.github.urmichm.placesearchktx.containers.common

import com.google.android.gms.maps.model.LatLngBounds

/**
 * A rectangle in geographical coordinates from points at the southwest and northeast corners.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#Bounds
 * */
data class BoundsContainer(
    val northeast : LatLngContainer,
    val southwest : LatLngContainer
){
    fun toLatLngBounds() : LatLngBounds {
        return LatLngBounds(southwest.toLatLng(), northeast.toLatLng())
    }
}