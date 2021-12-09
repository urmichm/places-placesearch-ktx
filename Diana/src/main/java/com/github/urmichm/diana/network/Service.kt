package com.github.urmichm.diana.network

import com.github.urmichm.diana.containers.NearbySearchContainer
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
private val NEARBY_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/"


/**
 * @details https://developers.google.com/maps/documentation/places/web-service/search#PlaceSearchRequests
 * */
interface DianaService{

    /**
     * Required parameters
     * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby
     * @param key — Your application's API key. This key identifies your application. See Get a key for more information.
     * @param location — The latitude/longitude around which to retrieve place information. This must be specified as latitude,longitude.
     * @param radius — Defines the distance (in meters) within which to return place results. Note that radius must not be included if [rankby]=distance (described under Optional parameters below) is specified.
     * @param rankby - Specifies the order in which results are listed
     * */
    @GET("nearbysearch/json")
    fun nearbySearch(
        @Query("key") key : String,
        @Query("location") location : String,
        @Query("type") type : String,
        @Query("rankby") rankby : String
    ) : Deferred<NearbySearchContainer>

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
object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(NEARBY_SEARCH_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val diana = retrofit.create(DianaService::class.java) as DianaService
}

