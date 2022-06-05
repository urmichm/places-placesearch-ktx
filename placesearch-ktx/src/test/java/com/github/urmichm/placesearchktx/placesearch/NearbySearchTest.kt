package com.github.urmichm.placesearchktx.placesearch

import com.github.urmichm.placesearchktx.Diana
import com.google.android.gms.maps.model.LatLng
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.IllegalArgumentException

class NearbySearchTest{

    private lateinit var d :Diana
    @Before
    fun initDiana(){
        d = Diana("YOUR_API_KEY")
    }


    @Test
    fun minPriceIsOutOfRange(){
        val nearbySearch = NearbySearch.Builder(d)
            .setMinPrice(6)
            .setLocation(LatLng(0.0,0.0))

        assertThrows("minPrice is out of possible range.", IllegalArgumentException::class.java) {
            nearbySearch.build()
        }
    }

    @Test
    fun maxPriceIsOutOfRange(){
        val nearbySearch = NearbySearch.Builder(d)
            .setMinPrice(2)
            .setMaxPrice(5)
            .setLocation(LatLng(0.0,0.0))

        assertThrows("maxPrice is out of possible range.", IllegalArgumentException::class.java) {
            nearbySearch.build()
        }
    }

    @Test
    fun radiusParameterIsOmittedWhenRankedByProminence(){
        val nearbySearch = NearbySearch.Builder(d)
            .setMinPrice(2)
            .setMaxPrice(4)
            .setLocation(LatLng(0.0,0.0))

        assertThrows("When prominence is specified, the radius parameter is required.", IllegalArgumentException::class.java) {
            nearbySearch.build()
        }
    }

    @Test
    fun radiusParameterIsGivenWhenRankedByDistance(){
        val nearbySearch = NearbySearch.Builder(d)
            .setRankBy(NearbySearch.Rankby.DISTANCE)
            .setRadius(5000)
            .setMinPrice(2)
            .setMaxPrice(4)
            .setLocation(LatLng(0.0,0.0))

        assertThrows("When using rankBy=distance, the radius parameter will not be accepted, and will result in an INVALID_REQUEST.", IllegalArgumentException::class.java) {
            nearbySearch.build()
        }
    }

    @Test
    fun validWhenRankedByDistanceAndRadiusOmitted(){
        val nearbySearch = NearbySearch.Builder(d)
            .setRankBy(NearbySearch.Rankby.DISTANCE)
            .setMinPrice(2)
            .setMaxPrice(4)
            .setLocation(LatLng(0.0,0.0))
            .build()

        assertNotNull(nearbySearch)
    }

    @Test
    fun validWhenRankedByProminenceAndRadiusGiven(){
        val nearbySearch = NearbySearch.Builder(d)
            .setRadius(1000)
            .setMinPrice(2)
            .setMaxPrice(4)
            .setLocation(LatLng(0.0,0.0))
            .build()

        assertNotNull(nearbySearch)
    }

    @Test
    fun locationNotProvided() {

        val diana = d

        val builder = NearbySearch.Builder(diana)

        assertThrows(Exception::class.java) {
            builder.build()
        }
    }
}
