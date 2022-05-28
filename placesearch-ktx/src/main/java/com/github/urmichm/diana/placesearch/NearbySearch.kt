package com.github.urmichm.diana.placesearch

import com.github.urmichm.diana.*
import com.github.urmichm.diana.containers.PlacesNearbySearchContainer
import com.github.urmichm.diana.network.Network
import com.github.urmichm.diana.toRequestString
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import kotlinx.coroutines.Deferred
import kotlin.Exception


/**
 * Nearby Search Google API
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby
 * @param builder The [Builder] object
 * */
class NearbySearch private constructor(private val builder : Builder){

    /**
     * [Diana] object with general settings
     * */
    private val diana : Diana = builder.diana

    /**
     *  The required parameter.
     *  The [LatLng] object describing latitude/longitude around which to retrieve place information.
     * */
    private val location :LatLng = builder.location

    /**
     * The text string on which to search, for example: "restaurant" or "123 Main Street".
     * This must be a place name, address, or category of establishments.
     * If this parameter is omitted, places with a business_status of CLOSED_TEMPORARILY or CLOSED_PERMANENTLY will not be returned.
     * */
    private val keyword :String? = builder.keyword

    /**
     * The language in which to return results.
     * */
    private val language :String? = builder.language

    /**
     * Restricts results to only those places within the specified range.
     * Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
     * */
    private val maxPrice :Int? = builder.maxPrice

    /**
     * Restricts results to only those places within the specified range.
     * Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
     * */
    private val minPrice :Int? = builder.minPrice

    /**
     * Returns only those places that are open for business at the time the query is sent.
     * Places that do not specify opening hours in the Google Places database will not be returned if you include this parameter in your query.
     * */
    private val openNow :Boolean? = builder.openNow

    /**
     * Returns up to 20 results from a previously run search.
     * Setting a pagetoken parameter will execute a search with the same parameters used previously — all parameters other than pagetoken will be ignored.
     * */
    private val pageToken :String? = builder.pageToken

    /**
     * Defines the distance (in meters) within which to return place results.
     * Note that radius must not be included if [rankBy]=distance (described under Optional parameters below) is specified.
     * */
    private val radius :Int? = builder.radius

    /**
     * Specifies the order in which results are listed
     * */
    private val rankBy :Diana.Rankby = builder.rankBy

    /**
     * Restricts the results to places matching the specified type. Only one type may be specified.
     * If more than one type is provided, all types following the first entry are ignored.
     * */
    private val type : Place.Type? = builder.type

    /**
     * The builder class for [NearbySearch] class
     * @param diana [Diana] object with general settings
     * */
    class Builder(val diana : Diana){

        /**
         *  The required parameter.
         *  The [LatLng] object describing latitude/longitude around which to retrieve place information.
         * */
        lateinit var location : LatLng

        /**
         * The text string on which to search, for example: "restaurant" or "123 Main Street".
         * This must be a place name, address, or category of establishments.
         * If this parameter is omitted, places with a business_status of CLOSED_TEMPORARILY or CLOSED_PERMANENTLY will not be returned.
         * */
        var keyword :String? = null

        /**
         * The language in which to return results.
         * */
        var language :String? = null

        /**
         * Restricts results to only those places within the specified range.
         * Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
         * */
        var maxPrice :Int? = null

        /**
         * Restricts results to only those places within the specified range.
         * Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
         * */
        var minPrice :Int? = null

        /**
         * Returns only those places that are open for business at the time the query is sent.
         * Places that do not specify opening hours in the Google Places database will not be returned if you include this parameter in your query.
         * */
        var openNow :Boolean? = null

        /**
         * Returns up to 20 results from a previously run search.
         * Setting a pagetoken parameter will execute a search with the same parameters used previously — all parameters other than pagetoken will be ignored.
         * */
        var pageToken :String? = null

        /**
         * Defines the distance (in meters) within which to return place results.
         * Note that radius must not be included if [rankBy]=distance (described under Optional parameters below) is specified.
         * */
        var radius :Int? = null

        /**
         * Specifies the order in which results are listed
         * */
        var rankBy :Diana.Rankby = Diana.Rankby.PROMINENCE

        /**
         * Restricts the results to places matching the specified type. Only one type may be specified.
         * If more than one type is provided, all types following the first entry are ignored.
         * */
        var type : Place.Type? = null

        /**
         * The build method to create a [NearbySearch] object
         * @return [NearbySearch] object created according to the builder settings.
         * */
        fun build() : NearbySearch {
            return NearbySearch(this)
        }
    }

    /**
     * Make a call to Nearby Search.
     * @return [PlacesNearbySearchContainer] container object on success, null otherwise
     * */
    suspend fun call() : PlacesNearbySearchContainer? {

        val message = validate()
        if(!message.isValid)  throw Exception(message.message)

        val nearby: Deferred<PlacesNearbySearchContainer> =
            Network.diana.nearbySearch(
                key = diana.key,
                location = location.toRequestString(),
                keyword = keyword,
                language = language,
                maxPrice = maxPrice,
                minPrice = minPrice,
                openNow = openNow,
                pageToken = pageToken,
                radius = radius,
                rankBy = rankBy.name.lowercase(),
                type = type
            )

        return try {
            val response = nearby.await()
            response
        } catch (e: Exception) {
            println("Exception: $e");
            null
        }
    }

    /**
     * Validate parameters before calling the server
     * @return The [Message] object based onn the validation.
     * */
    fun validate() :Message{
        minPrice?.apply {
            if( priceNotInRange(this) )
                return Message("minPrice is out of possible range.", false)
        }
        maxPrice?.apply {
            if( priceNotInRange(this) )
                return Message("maxPrice is out of possible range.", false)
        }

        when(rankBy){
            Diana.Rankby.PROMINENCE -> {
                if(radius == null)
                    return Message(
                        "When prominence is specified, the radius parameter is required.",
                        false)

            }
            Diana.Rankby.DISTANCE -> {
                if(radius != null)
                    return Message(
                        "When using rankBy=distance, the radius parameter will not be accepted, and will result in an INVALID_REQUEST.",
                        false)
            }
        }

        return Message("OK", true)
    }




}