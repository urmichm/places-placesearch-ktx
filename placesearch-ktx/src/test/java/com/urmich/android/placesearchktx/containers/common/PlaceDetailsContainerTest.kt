// Copyright 2020 Urmich Mikhail
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

package com.urmich.android.placesearchktx.containers.common

import com.google.android.libraries.places.api.model.Place
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class PlaceDetailsContainerTest {

    private lateinit var container : PlaceDetailsContainer

    @Before
    fun setup(){
        container = PlaceDetailsContainer(
            placeId = "some-id",
            name = "some-name",
            formattedAddress = null,
            businessStatus = "OPERATIONAL",
            priceLevel = 4,
            rating = 4.5,
            userRatingsTotal = 4234,
            vicinity = "vicinity",
            types = listOf("BAR", "park"),
            geometry = makeGeometry(),
            photos = makePhotos(),
            openingHours = OpeningHoursContainer(openNow = true),
            plusCode = null,
            iconUrl = null,
            iconBackgroundColor = null,
            iconMaskBaseUri = null,
            reference = null,
            scope = null
        )
    }

    private fun makePhotos(): List<PhotoMetadataContainer>? {
        // TODO: TBD
        return null
    }

    private fun makeGeometry(): GeometryContainer {
        return GeometryContainer(
            LatLngContainer(0.0,0.0,),
            BoundsContainer(LatLngContainer(0.0,0.0), LatLngContainer(0.0,0.0))
        )
    }

    @Test
    fun testToString() {
        assertEquals("${container.name}[${container.rating}]", container.toString())
    }

    @Test
    fun isOpen() {
        assertEquals(container.openingHours?.openNow, container.isOpen())
    }

    @Test
    fun asPlace() {
        val place = container.asPlace()

        assertNotNull(place.businessStatus)
        assertEquals(container.businessStatus, place.businessStatus!!.name)
        assertEquals(container.rating, place.rating)
        assertEquals( container.vicinity, place.address)
        assertEquals(container.placeId, place.id)
        assertEquals(container.userRatingsTotal, place.userRatingsTotal)

        assertNotNull(place.types)
        assertEquals(2, place.types!!.size)
        assertTrue(place.types!!.contains(Place.Type.BAR))
        assertTrue(place.types!!.contains(Place.Type.PARK))
        assertFalse(place.types!!.contains(Place.Type.PARKING))
    }
}