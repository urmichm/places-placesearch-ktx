package com.github.urmichm.placesearchktx

import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionsKtTest() {

    @Test
    fun latLng_To_RequestString() {
        val latLng = LatLng(52.12, 23.123)
        assertEquals("52.12,23.123",latLng.toRequestString())

        assertEquals("52.12,-23.123",LatLng(52.12, -23.123).toRequestString())

        val latLngNull : LatLng? = null
        assertEquals(null,latLngNull?.toRequestString())
    }

    @Test
    fun placeField_To_RequestString() {
        assertEquals("name", Place.Field.NAME.toRequestString())
        assertEquals("rating", Place.Field.RATING.toRequestString())
        assertEquals("price_level", Place.Field.PRICE_LEVEL.toRequestString())
        assertEquals("icon_background_color", Place.Field.ICON_BACKGROUND_COLOR.toRequestString())

        assertEquals("address_component", Place.Field.ADDRESS_COMPONENTS.toRequestString())
        assertEquals("formatted_address", Place.Field.ADDRESS.toRequestString())
        assertEquals("geometry/viewport", Place.Field.VIEWPORT.toRequestString())
        assertEquals("geometry/location", Place.Field.LAT_LNG.toRequestString())
        assertEquals("icon_mask_base_uri", Place.Field.ICON_URL.toRequestString())
        assertEquals("photos", Place.Field.PHOTO_METADATAS.toRequestString())
        assertEquals("place_id", Place.Field.ID.toRequestString())
        assertEquals("type", Place.Field.TYPES.toRequestString())
        assertEquals("international_phone_number", Place.Field.PHONE_NUMBER.toRequestString())
        assertEquals("website", Place.Field.WEBSITE_URI.toRequestString())
    }
}