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

import com.google.android.gms.maps.model.LatLngBounds

/**
 * A rectangle in geographical coordinates from points at the southwest and northeast corners.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#Bounds
 * */
data class BoundsContainer(
    val northeast : LatLngContainer,
    val southwest : LatLngContainer
){
    fun toLatLngBounds() : LatLngBounds {
        return LatLngBounds(southwest.toLatLng(), northeast.toLatLng())
    }
}