package com.urmich.android.placesearchktx.containers.common

import org.junit.Assert.*

import org.junit.Test

class LatLngContainerTest {

    @Test
    fun toLatLng() {
        val delta = 0.0

        val latLngContainer = LatLngContainer(lat = 47.523, lng=-5.231)
        val latLng = latLngContainer.toLatLng()

        assertEquals(latLngContainer.lat, latLng.latitude, delta)
        assertEquals(latLngContainer.lng, latLng.longitude, delta)
    }
}