package com.github.urmichm.diana.containers

import com.google.android.gms.maps.model.LatLng

data class LatLngContainer(
    val lat : Double,
    val lng : Double
) {

    fun toLatLng():LatLng{
        return LatLng(lat, lng)
    }
}