package com.github.urmichm.placesearchktx.network

import com.github.urmichm.placesearchktx.Diana.Companion.OUTPUT_FORMAT
import com.github.urmichm.placesearchktx.containers.PlacesFindPlaceContainer
import com.github.urmichm.placesearchktx.containers.PlacesNearbySearchContainer
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
 * Nearby search requests
 * Nearby Search and Text Search return all of the available data fields for the selected place (a subset of the supported fields), and you will be billed accordingly
 * There is no way to constrain Nearby Search or Text Search to only return specific fields.
 * To keep from requesting (and paying for) data that you don't need, use a Find Place request instead.
 * */
private val URL = "https://maps.googleapis.com/maps/api/place/"


/**
 * @details https://developers.google.com/maps/documentation/places/web-service/search#PlaceSearchRequests
 * */
internal interface DianaService{

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
     * @return [PlacesNearbySearchContainer] object is returned wrapped into [Deferred] class
     * */
    @GET("nearbysearch/${OUTPUT_FORMAT}")
    fun nearbySearch(
        @Query("key") key : String,
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
    ) : Deferred<PlacesNearbySearchContainer>

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
     * [PlacesFindPlaceContainer] object is returned wrapped into [Deferred] class
     * */
    @GET("findplacefromtext/${OUTPUT_FORMAT}")
    fun findPlace(
        @Query("key") key : String,

        @Query("input") input : String,
        @Query("inputtype") inputtype :String,

        @Query("fields") fields :String?,
        @Query("language") language :String?,
        @Query("locationbias") locationbias :String?
    ) : Deferred<PlacesFindPlaceContainer>
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
 * [Network.diana].nearbySearch(..)
 */
internal object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val diana = retrofit.create(DianaService::class.java) as DianaService
}

