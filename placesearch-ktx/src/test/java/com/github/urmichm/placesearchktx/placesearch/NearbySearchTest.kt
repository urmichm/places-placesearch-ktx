package com.github.urmichm.placesearchktx.placesearch

import com.github.urmichm.placesearchktx.Diana
import com.google.android.gms.maps.model.LatLng
import org.junit.Assert.*
import org.junit.Test

class NearbySearchTest{

    @Test
    fun minPriceIsOutOfRange(){
        val nearbySearch = NearbySearch.Builder(Diana.Builder("").build())
            .setMinPrice(6)
            .setLocation(LatLng(0.0,0.0))
            .build()
        val m = nearbySearch.validate()

        assertFalse(m.isValid)
        assertEquals("minPrice is out of possible range.", m.message)
    }

    @Test
    fun maxPriceIsOutOfRange(){
        val nearbySearch = NearbySearch.Builder(Diana.Builder("").build())
            .setMinPrice(2)
            .setMaxPrice(5)
            .setLocation(LatLng(0.0,0.0))
            .build()
        val m = nearbySearch.validate()

        assertFalse(m.isValid)
        assertEquals("maxPrice is out of possible range.", m.message)
    }

    @Test
    fun radiusParameterIsOmittedWhenRankedByProminence(){
        val nearbySearch = NearbySearch.Builder(Diana.Builder("").build())
            .setMinPrice(2)
            .setMaxPrice(4)
            .setLocation(LatLng(0.0,0.0))
            .build()
        val m = nearbySearch.validate()

        assertFalse(m.isValid)
        assertEquals("When prominence is specified, the radius parameter is required.", m.message)
    }

    @Test
    fun radiusParameterIsGivenWhenRankedByDistance(){
        val nearbySearch = NearbySearch.Builder(Diana.Builder("").build())
            .setRankBy(Diana.Rankby.DISTANCE)
            .setRadius(5000)
            .setMinPrice(2)
            .setMaxPrice(4)
            .setLocation(LatLng(0.0,0.0))
            .build()
        val m = nearbySearch.validate()

        assertFalse(m.isValid)
        assertEquals("When using rankBy=distance, the radius parameter will not be accepted, and will result in an INVALID_REQUEST.", m.message)
    }

    @Test
    fun validWhenRankedByDistanceAndRadiusOmitted(){
        val nearbySearch = NearbySearch.Builder(Diana.Builder("").build())
            .setRankBy(Diana.Rankby.DISTANCE)
            .setMinPrice(2)
            .setMaxPrice(4)
            .setLocation(LatLng(0.0,0.0))
            .build()
        val m = nearbySearch.validate()

        assertTrue(m.isValid)
        assertEquals("OK", m.message)
    }

    @Test
    fun validWhenRankedByProminenceAndRadiusGiven(){
        val nearbySearch = NearbySearch.Builder(Diana.Builder("").build())
            .setRadius(1000)
            .setMinPrice(2)
            .setMaxPrice(4)
            .setLocation(LatLng(0.0,0.0))
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
