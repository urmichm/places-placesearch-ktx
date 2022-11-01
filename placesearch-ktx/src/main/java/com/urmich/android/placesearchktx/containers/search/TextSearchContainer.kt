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

package com.urmich.android.placesearchktx.containers.search

import com.urmich.android.placesearchktx.containers.PlaceSearchContainer
import com.urmich.android.placesearchktx.containers.common.PlaceDetailsContainer
import com.squareup.moshi.Json

/**
 * Container fro nearBySearch request.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-text#PlacesTextSearchResponse
 * */
data class TextSearchContainer (
    @Json(name="html_attributions")
    override val htmlAttributions : List<String>,
    @Json(name="results")
    override val places :List<PlaceDetailsContainer>,
    @Json(name="status")
    override val status : String,
    @Json(name="error_message")
    override val errorMessage : String?,
    @Json(name="info_messages")
    override val infoMessages : List<String>?,
    @Json(name="next_page_token")
    override val nextPageToken : String?
) : PlaceSearchContainer() {

    override fun toString(): String =  super.toString()

}

