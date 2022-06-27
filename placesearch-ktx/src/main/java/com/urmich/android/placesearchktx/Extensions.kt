package com.urmich.android.placesearchktx

import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place


/**
 * Convert LatLng object to String for API requests
 * @return String object of LatLng fot API requests
 * */
internal fun LatLng.toRequestString():String{
    return "${latitude},${longitude}"
}

/**
 * Convert [Place.Field] object to String for API requests
 * @return String object of [Place.Field] fot API requests
 * */
internal fun Place.Field.toRequestString():String{
    return when(this){
        Place.Field.ADDRESS_COMPONENTS -> "address_component"
        Place.Field.ADDRESS -> "formatted_address"
        Place.Field.VIEWPORT -> "geometry/viewport"
        Place.Field.LAT_LNG -> "geometry/location"
        Place.Field.ICON_URL -> "icon_mask_base_uri"
        Place.Field.PHOTO_METADATAS -> "photos"
        Place.Field.ID -> "place_id"
        Place.Field.TYPES -> "type"
        Place.Field.PHONE_NUMBER -> "international_phone_number"
        Place.Field.WEBSITE_URI -> "website"
        else -> this.toString().lowercase()
    }
}

