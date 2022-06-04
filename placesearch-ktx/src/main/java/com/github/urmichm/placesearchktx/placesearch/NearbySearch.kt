package com.github.urmichm.placesearchktx.placesearch

import com.github.urmichm.placesearchktx.*
import com.github.urmichm.placesearchktx.containers.NearbySearchContainer
import com.github.urmichm.placesearchktx.network.Network
import com.github.urmichm.placesearchktx.toRequestString
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
    private val location :String = builder.getLocation()

    /**
     * The text string on which to search, for example: "restaurant" or "123 Main Street".
     * This must be a place name, address, or category of establishments.
     * If this parameter is omitted, places with a business_status of CLOSED_TEMPORARILY or CLOSED_PERMANENTLY will not be returned.
     * */
    private val keyword :String? = builder.getKeyword()

    /**
     * The language in which to return results.
     * */
    private val language :String? = builder.getLanguage()

    /**
     * Restricts results to only those places within the specified range.
     * Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
     * */
    private val maxPrice :Int? = builder.getMaxPrice()

    /**
     * Restricts results to only those places within the specified range.
     * Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
     * */
    private val minPrice :Int? = builder.getMinPrice()

    /**
     * Returns only those places that are open for business at the time the query is sent.
     * Places that do not specify opening hours in the Google Places database will not be returned if you include this parameter in your query.
     * */
    private val openNow :Boolean? = builder.getOpenNow()

    /**
     * Returns up to 20 results from a previously run search.
     * Setting a pagetoken parameter will execute a search with the same parameters used previously — all parameters other than pagetoken will be ignored.
     * */
    private val pageToken :String? = builder.getPageToken()

    /**
     * Defines the distance (in meters) within which to return place results.
     * Note that radius must not be included if [rankBy]=distance (described under Optional parameters below) is specified.
     * */
    private val radius :Int? = builder.getRadius()

    /**
     * Specifies the order in which results are listed
     * */
    private val rankBy : Rankby? = builder.getRankBy()

    /**
     * Restricts the results to places matching the specified type. Only one type may be specified.
     * If more than one type is provided, all types following the first entry are ignored.
     * */
    private val type : Place.Type? = builder.getType()

    /**
     * The builder class for [NearbySearch] class
     * @param diana [Diana] object with general settings
     * */
    class Builder(val diana : Diana){

        /**
         *  The required parameter.
         *  The [LatLng] object describing latitude/longitude around which to retrieve place information.
         * */
        private lateinit var location : String

        /**
         * Getter for [location]
         * @return The value of [location]
         * */
        fun getLocation() = location

        /**
         * Setter for [location]
         * @param location Location provided in form of [LatLng] object
         * */
        fun setLocation(location : LatLng):Builder = apply{
            this.location = location.toRequestString()
        }

        /**
         * The text string on which to search, for example: "restaurant" or "123 Main Street".
         * This must be a place name, address, or category of establishments.
         * If this parameter is omitted, places with a business_status of CLOSED_TEMPORARILY or CLOSED_PERMANENTLY will not be returned.
         * */
        private var keyword :String? = null

        /**
         * Getter for [keyword]
         * @return The value of [keyword]
         * */
        fun getKeyword() = keyword

        /**
         * Setter for [keyword]
         * @param keyword New value for [keyword]
         * */
        fun setKeyword(keyword : String) : Builder = apply{
            this.keyword = keyword
        }

        /**
         * The language in which to return results.
         * */
        private var language :String? = null

        /**
         * Getter for [language]
         * @return The value of [language]
         * */
        fun getLanguage() = language

        /**
         * Setter for [language]
         * @param language New value for [language]
         * */
        fun setLanguage(language :String) :Builder = apply {
            this.language = language
        }

        /**
         * Restricts results to only those places within the specified range.
         * Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
         * */
        private var maxPrice :Int? = null

        /**
         * Getter for [maxPrice]
         * @return The value of [maxPrice]
         * */
        fun getMaxPrice() = maxPrice

        /**
         * Setter for [maxPrice]
         * @param maxPrice The new value for [maxPrice]
         * */
        fun setMaxPrice(maxPrice :Int) :Builder = apply{
            this.maxPrice = maxPrice
        }

        /**
         * Restricts results to only those places within the specified range.
         * Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
         * */
        private var minPrice :Int? = null

        /**
         * Getter for [minPrice]
         * @return The value of [minPrice]
         * */
        fun getMinPrice() = minPrice

        /**
         * Setter for [minPrice]
         * @param minPrice The new value for [minPrice]
         * */
        fun setMinPrice(minPrice :Int) :Builder = apply{
            this.minPrice = minPrice
        }

        /**
         * Returns only those places that are open for business at the time the query is sent.
         * Places that do not specify opening hours in the Google Places database will not be returned if you include this parameter in your query.
         * */
        private var openNow :Boolean? = null

        /**
         * Getter for [openNow]
         * @return The value of [openNow]
         * */
        fun getOpenNow() = openNow

        /**
         * Setter for [openNow]
         * @param openNow The new value for [openNow]
         * */
        fun setOpenNow(openNow :Boolean) :Builder = apply{
            this.openNow = openNow
        }

        /**
         * Returns up to 20 results from a previously run search.
         * Setting a page token parameter will execute a search with the same parameters used previously — all parameters other than pagetoken will be ignored.
         * */
        private var pageToken :String? = null

        /**
         * Getter for [pageToken]
         * @return The value of [pageToken]
         * */
        fun getPageToken() = pageToken

        /**
         * Setter for [pageToken]
         * @param pageToken The new value for [pageToken]
         * */
        fun setPageToken(pageToken :String) :Builder = apply{
            this.pageToken = pageToken
        }


        /**
         * Defines the distance (in meters) within which to return place results.
         * Note that radius must not be included if [rankBy]=distance (described under Optional parameters below) is specified.
         * */
        private var radius :Int? = null

        /**
         * Getter for [radius]
         * @return The value of [radius]
         * */
        fun getRadius() = radius

        /**
         * Setter for [radius]
         * @param radius The new value for [radius]
         * */
        fun setRadius(radius :Int) :Builder = apply{
            this.radius = radius
        }

        /**
         * Specifies the order in which results are listed
         * */
        private var rankBy : Rankby? = Rankby.PROMINENCE

        /**
         * Getter for [rankBy]
         * @return The value of [rankBy]
         * */
        fun getRankBy() = rankBy

        /**
         * Setter for [rankBy]
         * @param rankBy The new value for [rankBy]
         * */
        fun setRankBy(rankBy : Rankby) :Builder = apply{
            this.rankBy = rankBy
        }

        /**
         * Restricts the results to places matching the specified type. Only one type may be specified.
         * If more than one type is provided, all types following the first entry are ignored.
         * */
        private var type : Place.Type? = null

        /**
         * Getter for [type]
         * @return The value of [type]
         * */
        fun getType() = type

        /**
         * Setter for [type]
         * @param type The new value for [type]
         * */
        fun setType(type :Place.Type) :Builder = apply{
            this.type = type
        }

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
     * @return [NearbySearchContainer] container object on success, null otherwise
     * */
    suspend fun call() : NearbySearchContainer? {

        // TODO: validate on build, not on call!
        val message = validate()
        if(!message.isValid)  throw Exception(message.message)

        val nearby: Deferred<NearbySearchContainer> =
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
                rankBy = rankBy.toString(),
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
            Rankby.PROMINENCE -> {
                if(radius == null)
                    return Message(
                        "When prominence is specified, the radius parameter is required.",
                        false)

            }
            Rankby.DISTANCE -> {
                if(radius != null)
                    return Message(
                        "When using rankBy=distance, the radius parameter will not be accepted, and will result in an INVALID_REQUEST.",
                        false)
            }
        }

        return Message("OK", true)
    }

    /**
     * Rankby enumeration for [rankBy] parameter
     * */
    enum class Rankby {
        PROMINENCE,
        DISTANCE;

        override fun toString(): String {
            return this.name.lowercase()
        }
    }


}