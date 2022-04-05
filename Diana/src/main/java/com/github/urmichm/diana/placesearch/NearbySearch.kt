package com.github.urmichm.diana.placesearch

import com.github.urmichm.diana.Diana
import com.github.urmichm.diana.containers.PlacesNearbySearchContainer
import com.github.urmichm.diana.network.Network
import com.google.android.libraries.places.api.model.Place
import kotlinx.coroutines.Deferred
import java.lang.Exception


/**
 * @brief Nearby Search Google API
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby
 * */
class NearbySearch private constructor(private val builder : NearbySearchBuilder){

    private val diana : Diana = builder.diana

    /** Required parameters **/
    private val location :String = builder.location

    /** Optional parameters **/
    private val keyword :String? = builder.keyword
    // https://developers.google.com/maps/faq#languagesupport
    private val language :String? = builder.language
    private val maxPrice :Int? = builder.maxPrice
    private val minPrice :Int? = builder.minPrice
    private val openNow :Boolean? = builder.openNow
    private val pageToken :String? = builder.pageToken
    private val radius :Int? = builder.radius
    private val rankBy :Diana.Rankby? = builder.rankBy
    private val type : Place.Type? = builder.type

    class NearbySearchBuilder(val diana : Diana){

        lateinit var location :String

        var keyword :String? = null
        var language :String? = null
        var maxPrice :Int? = null
        var minPrice :Int? = null
        var openNow :Boolean? = null
        var pageToken :String? = null
        var radius :Int? = null
        var rankBy :Diana.Rankby? = null
        var type : Place.Type? = null

        fun build() : NearbySearch {
            return NearbySearch(this)
        }
    }

    /**
     * @brief makes a call to Nearby Search
     * TODO: add parameter validation
     * */
    suspend fun call() : PlacesNearbySearchContainer? {

        val nearby: Deferred<PlacesNearbySearchContainer> =
            Network.diana.nearbySearch(
                key = diana.key,
                location = location,
                keyword = keyword,
                language = language,
                maxPrice = maxPrice,
                minPrice = minPrice,
                openNow = openNow,
                pageToken = pageToken,
                radius = radius,
                rankBy = rankBy?.name?.lowercase(),
                type = type
            )

        return try {
            val response = nearby.await()
            response
        } catch (e: Exception) {
            println("Exceptioin e: $e");
            null
        }
    }

}