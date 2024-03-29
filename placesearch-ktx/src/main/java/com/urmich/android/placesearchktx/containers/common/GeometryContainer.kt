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

package com.urmich.android.placesearchktx.containers.common

/**
 * Container for a geometry object. Class describes the location of a Place.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#Geometry
 * */
data class GeometryContainer(
    val location : LatLngContainer?,
    val viewport : BoundsContainer?
)