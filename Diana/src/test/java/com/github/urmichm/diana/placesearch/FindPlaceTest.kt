package com.github.urmichm.diana.placesearch

import com.github.urmichm.diana.Diana
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class FindPlaceTest {

    private lateinit var d : Diana

    @Before
    fun initDiana(){
        d = Diana.Builder("YOUR_API_KEY").build()
    }

    @Test
    fun findPlaceBuiltSuccessfully() {
        val findPlace = FindPlace.Builder(d)
            .setInput("input")
            .setInputType(FindPlace.InputType.TEXTQUERY)
            .build()

        assertNotNull(findPlace)
    }

    @Test
    fun findPlaceBuildFailedInputNotProvided() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            FindPlace.Builder(d)
                .setInputType(FindPlace.InputType.TEXTQUERY)
                .build()
        }
    }

    @Test
    fun findPlaceBuildFailedInputTypeNotProvided() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            FindPlace.Builder(d)
                .setInput("input")
                .build()
        }
    }

    @Test
    fun findPlaceBuildFailed() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            FindPlace.Builder(d)
                .build()
        }
    }

    @Test
    fun settersAndGettersRequiredFields() {
        val findPlaceBuilder = FindPlace.Builder(d)
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
        val findPlaceBuilder = FindPlace.Builder(d)
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
        val findPlaceBuilder = FindPlace.Builder(d)
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
        val findPlaceBuilder = FindPlace.Builder(d)
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

        val findPlaceBuilder = FindPlace.Builder(d)
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