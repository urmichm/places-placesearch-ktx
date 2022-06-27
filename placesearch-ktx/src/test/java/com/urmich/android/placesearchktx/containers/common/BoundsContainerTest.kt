package com.urmich.android.placesearchktx.containers.common

import org.junit.Assert.*

import org.junit.Test

class BoundsContainerTest {

    @Test
    fun toLatLngBounds() {
        val delta = 0.0

        val boundsContainer = BoundsContainer(
            northeast = LatLngContainer(lat = 53.456, lng = 21.908),
            southwest = LatLngContainer(lat=52.527, lng=-12.248)
        )

        val latLngBounds = boundsContainer.toLatLngBounds()

        // North East
        assertEquals(boundsContainer.northeast.lat, latLngBounds.northeast.latitude, delta)
        assertEquals(boundsContainer.northeast.lng, latLngBounds.northeast.longitude, delta)

        // South West
        assertEquals(boundsContainer.southwest.lat, latLngBounds.southwest.latitude, delta)
        assertEquals(boundsContainer.southwest.lng, latLngBounds.southwest.longitude, delta)
    }
}