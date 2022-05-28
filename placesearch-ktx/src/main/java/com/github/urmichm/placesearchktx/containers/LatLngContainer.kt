package com.github.urmichm.placesearchktx.containers

import com.google.android.gms.maps.model.LatLng

/**
 * Container for a [LatLng] object
 * @param lat latitude
 * @param lng longitude
 * */
data class LatLngContainer(
    val lat : Double,
    val lng : Double
) {

    fun toLatLng():LatLng{
        return LatLng(lat, lng)
    }
}