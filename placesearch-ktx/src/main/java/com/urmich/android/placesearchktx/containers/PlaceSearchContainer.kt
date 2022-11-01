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

package com.urmich.android.placesearchktx.containers

import com.urmich.android.placesearchktx.containers.common.PlaceDetailsContainer


abstract class PlaceSearchContainer {

    /**
     * May contain a set of attributions about this listing which must be displayed to the user
     * (some listings may not have attribution).
     * */
    abstract val htmlAttributions : List<String>?
    /**
     * Contains an array of places.
     * */
    abstract val places :List<PlaceDetailsContainer>
    /**
     * Contains the status of the request, and may contain debugging information to help you track down why the request failed.
     * */
    abstract val status : String
    /**
     * When the service returns a status code other than OK,
     * there may be an additional error_message field within the response object.
     * */
    abstract val errorMessage : String?
    /**
     * When the service returns additional information about the request specification,
     * there may be an additional info_messages field within the response object.
     * This field is only returned for successful requests. It may not always be returned, and its content is subject to change.
     * */
    abstract val infoMessages : List<String>?
    /**
     * Contains a token that can be used to return up to 20 additional results.
     * A next_page_token will not be returned if there are no additional results to display.
     * */
    abstract val nextPageToken : String?


    override fun toString(): String {
        val str = StringBuilder()
        str.appendLine("status: $status")
        errorMessage?.let{
            str.appendLine( "Error: $it" )
        }
        infoMessages?.forEach {
            str.appendLine(it)
        }
        places.let {
            str.appendLine("Places found: ${it.size}")
        }
        return str.toString()
    }
}