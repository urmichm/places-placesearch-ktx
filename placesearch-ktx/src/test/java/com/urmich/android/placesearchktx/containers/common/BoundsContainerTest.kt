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