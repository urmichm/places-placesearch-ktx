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

package com.urmich.android.placesearchktx.network

import com.urmich.android.placesearchktx.BuildConfig
import com.urmich.android.placesearchktx.containers.search.FindPlaceContainer
import com.urmich.android.placesearchktx.containers.search.NearbySearchContainer
import com.urmich.android.placesearchktx.containers.search.TextSearchContainer
import com.google.android.libraries.places.api.model.Place
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API key
 * */
private const val PLACES_API_KEY : String = BuildConfig.PLACES_API_KEY

/**
 * Nearby search requests
 * Nearby Search and Text Search return all of the available data fields for the selected place (a subset of the supported fields), and you will be billed accordingly
 * There is no way to constrain Nearby Search or Text Search to only return specific fields.
 * To keep from requesting (and paying for) data that you don't need, use a Find Place request instead.
 * */
private val URL = "https://maps.googleapis.com/maps/api/place/"

/**
 * Output format; indicates output in JavaScript Object Notation (JSON)
 * */
private const val OUTPUT_FORMAT = "json"

/**
 * @details https://developers.google.com/maps/documentation/places/web-service/search#PlaceSearchRequests
 * */
internal interface DianaService{


    /**
     * A Text Search returns information about a set of places based on a string — for example "pizza in New York" or "shoe stores near Ottawa" or "123 Main Street".
     * @details https://developers.google.com/maps/documentation/places/web-service/search-text
     * @param key Your application's API key. This key identifies your application.
     * @param query The text string on which to search, for example: "restaurant" or "123 Main Street". This must a place name, address, or category of establishments.
     * @param language The language in which to return results.
     * @param location The latitude/longitude around which to retrieve place information.
     * @param maxPrice Restricts results to only those places within the specified range.
     *                 Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
     * @param minPrice Restricts results to only those places within the specified range.
     *                 Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
     * @param openNow Returns only those places that are open for business at the time the query is sent.
     *                Places that do not specify opening hours in the Google Places database will not be returned if you include this parameter in your query.
     * @param pageToken Returns up to 20 results from a previously run search.
     *                  Setting a pagetoken parameter will execute a search with the same parameters used previously — all parameters other than pagetoken will be ignored.
     * @param radius Defines the distance (in meters) within which to return place results.
     *               Note that radius must not be included if [rankby]=distance (described under Optional parameters below) is specified.
     * @param region The region code, specified as a ccTLD ("top-level domain") two-character value.
     * @param type Restricts the results to places matching the specified type. Only one type may be specified.
     *             If more than one type is provided, all types following the first entry are ignored.
     * @return [TextSearchContainer] object is returned wrapped into [Deferred] class
     *
     * */
    @GET("textsearch/${OUTPUT_FORMAT}")
    fun textSearchAsync(
        @Query("key") key : String = PLACES_API_KEY,
        @Query("query") query : String,

        @Query("language") language :String?,
        @Query("location") location : String?,
        @Query("maxPrice") maxPrice :Int?,
        @Query("minPrice") minPrice :Int?,
        @Query("openNow") openNow :Boolean?,
        @Query("pageToken") pageToken :String?,
        @Query("radius") radius :Int?,
        @Query("region") region :String?,
        @Query("type") type : Place.Type?
    ): Deferred<TextSearchContainer>

    /**
     * A Nearby Search lets you search for places within a specified area. You can refine your search request by supplying keywords or specifying the type of place you are searching for.
     * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby
     * @param key Your application's API key. This key identifies your application.
     * @param location The latitude/longitude around which to retrieve place information. This must be specified as latitude,longitude.
     * @param keyword The text string on which to search, for example: "restaurant" or "123 Main Street".
     *                This must be a place name, address, or category of establishments.
     *                If this parameter is omitted, places with a business_status of CLOSED_TEMPORARILY or CLOSED_PERMANENTLY will not be returned.
     * @param language The language in which to return results.
     * @param maxPrice Restricts results to only those places within the specified range.
     *                 Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
     * @param minPrice Restricts results to only those places within the specified range.
     *                 Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
     * @param openNow Returns only those places that are open for business at the time the query is sent.
     *                Places that do not specify opening hours in the Google Places database will not be returned if you include this parameter in your query.
     * @param pageToken Returns up to 20 results from a previously run search.
     *                  Setting a pagetoken parameter will execute a search with the same parameters used previously — all parameters other than pagetoken will be ignored.
     * @param radius Defines the distance (in meters) within which to return place results.
     *               Note that radius must not be included if [rankby]=distance (described under Optional parameters below) is specified.
     * @param rankby Specifies the order in which results are listed
     * @param type Restricts the results to places matching the specified type. Only one type may be specified.
     *             If more than one type is provided, all types following the first entry are ignored.
     * @return [NearbySearchContainer] object is returned wrapped into [Deferred] class
     * */
    @GET("nearbysearch/${OUTPUT_FORMAT}")
    fun nearbySearchAsync(
        @Query("key") key : String = PLACES_API_KEY,
        @Query("location") location : String,
        @Query("keyword") keyword :String?,
        @Query("language") language :String?,
        @Query("maxPrice") maxPrice :Int?,
        @Query("minPrice") minPrice :Int?,
        @Query("openNow") openNow :Boolean?,
        @Query("pageToken") pageToken :String?,
        @Query("radius") radius :Int?,
        @Query("rankby") rankBy : String?,
        @Query("type") type : Place.Type?
    ) : Deferred<NearbySearchContainer>

    /**
     * A Find Place request takes a text input and returns a place. The input can be any kind of Places text data, such as a name, address, or phone number.
     * The request must be a string. A Find Place request using non-string data such as a lat/lng coordinate or plus code generates an error.
     *
     * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place
     *
     * @param key Your application's API key. This key identifies your application.
     *
     * @param input The text string on which to search, for example: "restaurant" or "123 Main Street".
     * This must be a place name, address, or category of establishments.
     * Any other types of input can generate errors and are not guaranteed to return valid results.
     * @param inputtype The type of input. This can be one of either [textquery] or [phonenumber].
     * Phone numbers must be in international format (prefixed by a plus sign ("+"), followed by the country code, then the phone number itself).
     *
     * @param fields Use the fields parameter to specify a list of place data types to return.
     * Fields are divided into three billing categories: Basic, Contact, and Atmosphere.
     * @param language The language in which to return results.
     * @param locationbias Prefer results in a specified area, by specifying either a radius plus lat/lng, or two lat/lng pairs representing the points of a rectangle.
     * If this parameter is not specified, the API uses IP address biasing by default.
     *
     * @return Candidate matches based on this string and order the results based on their perceived relevance
     * [FindPlaceContainer] object is returned wrapped into [Deferred] class
     * */
    @GET("findplacefromtext/${OUTPUT_FORMAT}")
    fun findPlaceAsync(
        @Query("key") key : String = PLACES_API_KEY,

        @Query("input") input : String,
        @Query("inputtype") inputtype :String,

        @Query("fields") fields :String?,
        @Query("language") language :String?,
        @Query("locationbias") locationbias :String?
    ) : Deferred<FindPlaceContainer>
}


/**
 * Build the Moshi object that Retrofit will be using,
 * making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


/**
 * Main entry point for network access.
 * [Network.service].nearbySearch(..)
 */
internal object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val service = retrofit.create(DianaService::class.java) as DianaService
}

