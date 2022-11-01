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

