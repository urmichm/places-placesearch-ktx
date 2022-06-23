package com.urmich.android.placesearchktx.containers.common

/**
 * Container for a geometry object. Class describes the location of a Place.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#Geometry
 * */
data class GeometryContainer(
    val location : LatLngContainer?,
    val viewport : BoundsContainer?
)