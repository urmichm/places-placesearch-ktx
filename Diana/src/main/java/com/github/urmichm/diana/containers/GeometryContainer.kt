package com.github.urmichm.diana.containers

/**
 * @brief class describing the location.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#Geometry
 * */
data class GeometryContainer(
    val location : LatLngContainer,
    val viewport : BoundsContainer
)