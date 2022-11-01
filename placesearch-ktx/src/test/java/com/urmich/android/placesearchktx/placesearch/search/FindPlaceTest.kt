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

package com.urmich.android.placesearchktx.placesearch.search

import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import org.junit.Assert.*

import org.junit.Test

class FindPlaceTest {

    @Test
    fun findPlaceBuiltSuccessfully() {
        val findPlace = FindPlace.Builder()
            .setInput("input")
            .setInputType(FindPlace.InputType.TEXTQUERY)
            .build()

        assertNotNull(findPlace)
    }

    @Test
    fun findPlaceBuildFailedInputNotProvided() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            FindPlace.Builder()
                .setInputType(FindPlace.InputType.TEXTQUERY)
                .build()
        }
    }

    @Test
    fun findPlaceBuildFailedInputTypeNotProvided() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            FindPlace.Builder()
                .setInput("input")
                .build()
        }
    }

    @Test
    fun findPlaceBuildFailed() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            FindPlace.Builder()
                .build()
        }
    }

    @Test
    fun settersAndGettersRequiredFields() {
        val findPlaceBuilder = FindPlace.Builder()
            .setInput("input")
            .setInputType(FindPlace.InputType.TEXTQUERY)


        assertEquals("input", findPlaceBuilder.getInput())
        assertEquals("textquery", findPlaceBuilder.getInputType().toString())

        findPlaceBuilder.setInputType(FindPlace.InputType.PHONENUMBER)
        assertEquals("phonenumber", findPlaceBuilder.getInputType().toString())

        assertNotNull(findPlaceBuilder.build())
    }

    @Test
    fun settersGettersLanguage(){
        val findPlaceBuilder = FindPlace.Builder()
            .setInput("input")
            .setInputType(FindPlace.InputType.TEXTQUERY)
            .setLanguage("en")


        assertEquals("input", findPlaceBuilder.getInput())
        assertEquals("textquery", findPlaceBuilder.getInputType().toString())
        assertEquals("en", findPlaceBuilder.getLanguage())

        assertNotNull(findPlaceBuilder.build())
    }

    @Test
    fun setterGettersFieldsUsingPlaceField(){
        val fieldsAsPlaceField = listOf(
            Place.Field.ADDRESS_COMPONENTS,
            Place.Field.ADDRESS,
            Place.Field.VIEWPORT,
            Place.Field.LAT_LNG ,
            Place.Field.ICON_URL,
            Place.Field.PHOTO_METADATAS,
            Place.Field.ID,
            Place.Field.TYPES,
            Place.Field.PHONE_NUMBER,
            Place.Field.WEBSITE_URI,
            Place.Field.RATING
            )
        val findPlaceBuilder = FindPlace.Builder()
            .setInput("input")
            .setInputType(FindPlace.InputType.TEXTQUERY)
            .setFields(fieldsAsPlaceField)


        val expectedFields = "address_component," +
                "formatted_address," +
                "geometry/viewport," +
                "geometry/location," +
                "icon_mask_base_uri," +
                "photos," +
                "place_id," +
                "type," +
                "international_phone_number," +
                "website," +
                "rating"

        assertEquals("input", findPlaceBuilder.getInput())
        assertEquals("textquery", findPlaceBuilder.getInputType().toString())
        assertEquals(expectedFields, findPlaceBuilder.getFields())

        assertNotNull(findPlaceBuilder.build())

    }

    @Test
    fun setterGettersFieldsUsingFindPlaceField(){
        val fieldsAsFindPlaceField = listOf(
            FindPlace.Field.FORMATTED_ADDRESS,
            FindPlace.Field.VIEWPORT,
            FindPlace.Field.LOCATION,
            FindPlace.Field.ICON_MASK_BASE_URI,
            FindPlace.Field.PHOTOS,
            FindPlace.Field.PLACE_ID,
            FindPlace.Field.TYPE,
            FindPlace.Field.INTERNATIONAL_PHONE_NUMBER,
            FindPlace.Field.WEBSITE,
            FindPlace.Field.OPEN_NOW,
            FindPlace.Field.RATING
        )
        val findPlaceBuilder = FindPlace.Builder()
            .setInput("input")
            .setInputType(FindPlace.InputType.TEXTQUERY)
            .setFields(fieldsAsFindPlaceField)


        val expectedFields = "formatted_address," +
                "geometry/viewport," +
                "geometry/location," +
                "icon_mask_base_uri," +
                "photos," +
                "place_id," +
                "type," +
                "international_phone_number," +
                "website," +
                "opening_hours/open_now," +
                "rating"

        assertEquals("input", findPlaceBuilder.getInput())
        assertEquals("textquery", findPlaceBuilder.getInputType().toString())
        assertEquals(expectedFields, findPlaceBuilder.getFields())

        assertNotNull(findPlaceBuilder.build())

    }

    @Test
    fun setterGettersLocationBias(){

        val south :Double = 12.34
        val west :Double = 23.45
        val north :Double = 34.56
        val east :Double = 45.67

        val lat :Double = 12.34
        val lng :Double = 45.12
        val radius :Double = 350.0

        val findPlaceBuilder = FindPlace.Builder()
            .setInput("input")
            .setInputType(FindPlace.InputType.TEXTQUERY)

        assertEquals("input", findPlaceBuilder.getInput())
        assertEquals("textquery", findPlaceBuilder.getInputType().toString())

        // Given  point:lat,lng
        findPlaceBuilder.setLocationBias(LatLng(lat, lng))
        assertEquals("point:${lat},${lng}", findPlaceBuilder.getLocationBias())

        // Given  rectangle:south,west|north,east
        findPlaceBuilder.setLocationBias(LatLng(south, west), LatLng(north, east))
        assertEquals("rectangle:$south,$west|$north,$east", findPlaceBuilder.getLocationBias())

        // Given circle:radius@lat,lng
        findPlaceBuilder.setLocationBias(radius, LatLng(lat, lng))
        assertEquals("circle:$radius@$lat,$lng", findPlaceBuilder.getLocationBias())


        // Given  rectangle:south,west|north,east
        val bounds = RectangularBounds.newInstance(LatLng(south, west), LatLng(north, east))
        findPlaceBuilder.setLocationBias(bounds)
        assertEquals("rectangle:$south,$west|$north,$east", findPlaceBuilder.getLocationBias())

        assertNotNull(findPlaceBuilder.build())
    }

}