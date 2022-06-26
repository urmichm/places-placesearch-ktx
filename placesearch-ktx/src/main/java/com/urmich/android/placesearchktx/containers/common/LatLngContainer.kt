package com.urmich.android.placesearchktx.containers.common

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