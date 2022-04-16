package com.github.urmichm.diana.placesearch

import com.github.urmichm.diana.Diana
import com.google.android.gms.maps.model.LatLng
import org.junit.Assert.*
import org.junit.Test

class NearbySearchTest{

    @Test
    fun minPriceIsOutOfRange(){
        val nearbySearch = NearbySearch.Builder(Diana.Builder("").build())
            .apply {
                location = LatLng(0.0,0.0)
                minPrice = 6
            }
            .build()
        val m = nearbySearch.validate()

        assertFalse(m.isValid)
        assertEquals("minPrice is out of possible range.", m.message)
    }

    @Test
    fun maxPriceIsOutOfRange(){
        val nearbySearch = NearbySearch.Builder(Diana.Builder("").build())
            .apply {
                location = LatLng(0.0,0.0)
                minPrice = 2
                maxPrice = 5
            }
            .build()
        val m = nearbySearch.validate()

        assertFalse(m.isValid)
        assertEquals("maxPrice is out of possible range.", m.message)
    }

    @Test
    fun radiusParameterIsOmittedWhenRankedByProminence(){
        val nearbySearch = NearbySearch.Builder(Diana.Builder("").build())
            .apply {
                location = LatLng(0.0,0.0)
                minPrice = 2
                maxPrice = 4
            }
            .build()
        val m = nearbySearch.validate()

        assertFalse(m.isValid)
        assertEquals("When prominence is specified, the radius parameter is required.", m.message)
    }

    @Test
    fun radiusParameterIsGivenWhenRankedByDistance(){
        val nearbySearch = NearbySearch.Builder(Diana.Builder("").build())
            .apply {
                location = LatLng(0.0,0.0)
                rankBy = Diana.Rankby.DISTANCE
                radius = 5000
                minPrice = 2
                maxPrice = 4
            }
            .build()
        val m = nearbySearch.validate()

        assertFalse(m.isValid)
        assertEquals("When using rankBy=distance, the radius parameter will not be accepted, and will result in an INVALID_REQUEST.", m.message)
    }

    @Test
    fun validWhenRankedByDistanceAndRadiusOmitted(){
        val nearbySearch = NearbySearch.Builder(Diana.Builder("").build())
            .apply {
                location = LatLng(0.0,0.0)
                rankBy = Diana.Rankby.DISTANCE
                minPrice = 2
                maxPrice = 4
            }
            .build()
        val m = nearbySearch.validate()

        assertTrue(m.isValid)
        assertEquals("OK", m.message)
    }

    @Test
    fun validWhenRankedByProminenceAndRadiusGiven(){
        val nearbySearch = NearbySearch.Builder(Diana.Builder("").build())
            .apply {
                location = LatLng(0.0,0.0)
                minPrice = 2
                maxPrice = 4
                radius = 1000
            }
            .build()
        val m = nearbySearch.validate()

        assertTrue(m.isValid)
        assertEquals("OK", m.message)
    }

    @Test
    fun locationNotProvided() {

        val diana = Diana.Builder("").build()

        val builder = NearbySearch.Builder(diana)

        assertThrows(Exception::class.java) {
            builder.build()
        }
    }
}
