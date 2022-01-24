package com.github.urmichm.diana.containers

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

/**
 * @brief class describing the location.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#Geometry
 * */
data class GeometryContainer(
    val location : LatLngContainer,
    val viewport : BoundsContainer
)