// Copyright 2022 Urmich Mikhail
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.urmich.android.placesearchktx.placesearch.search

import com.google.android.gms.maps.model.LatLng
import org.junit.Assert.*
import org.junit.Test
import kotlin.IllegalArgumentException

class NearbySearchTest{

    @Test
    fun minPriceIsOutOfRange(){
        val nearbySearch = NearbySearch.Builder()
            .setMinPrice(6)
            .setLocation(LatLng(0.0,0.0))

        assertThrows("minPrice is out of possible range.", IllegalArgumentException::class.java) {
            nearbySearch.build()
        }
    }

    @Test
    fun maxPriceIsOutOfRange(){
        val nearbySearch = NearbySearch.Builder()
            .setMinPrice(2)
            .setMaxPrice(5)
            .setLocation(LatLng(0.0,0.0))

        assertThrows("maxPrice is out of possible range.", IllegalArgumentException::class.java) {
            nearbySearch.build()
        }
    }

    @Test
    fun radiusParameterIsOmittedWhenRankedByProminence(){
        val nearbySearch = NearbySearch.Builder()
            .setMinPrice(2)
            .setMaxPrice(4)
            .setLocation(LatLng(0.0,0.0))

        assertThrows("When prominence is specified, the radius parameter is required.", IllegalArgumentException::class.java) {
            nearbySearch.build()
        }
    }

    @Test
    fun radiusParameterIsGivenWhenRankedByDistance(){
        val nearbySearch = NearbySearch.Builder()
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
        val nearbySearch = NearbySearch.Builder()
            .setRankBy(NearbySearch.Rankby.DISTANCE)
            .setMinPrice(2)
            .setMaxPrice(4)
            .setLocation(LatLng(0.0,0.0))
            .build()

        assertNotNull(nearbySearch)
    }

    @Test
    fun validWhenRankedByProminenceAndRadiusGiven(){
        val nearbySearch = NearbySearch.Builder()
            .setRadius(1000)
            .setMinPrice(2)
            .setMaxPrice(4)
            .setLocation(LatLng(0.0,0.0))
            .build()

        assertNotNull(nearbySearch)
    }

    @Test
    fun locationNotProvided() {

        val builder = NearbySearch.Builder()

        assertThrows(Exception::class.java) {
            builder.build()
        }
    }
}
