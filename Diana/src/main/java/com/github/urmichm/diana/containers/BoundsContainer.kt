package com.github.urmichm.diana.containers

import com.google.android.gms.maps.model.LatLngBounds

/**
 * @brief A rectangle in geographical coordinates from points at the southwest and northeast corners.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#Bounds
 * */
data class BoundsContainer(
    val northeast : LatLngBounds,
    val southwest : LatLngBounds
){

}