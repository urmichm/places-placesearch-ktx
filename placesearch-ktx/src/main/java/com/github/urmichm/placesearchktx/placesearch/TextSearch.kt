package com.github.urmichm.placesearchktx.placesearch

import com.github.urmichm.placesearchktx.containers.TextSearchContainer
import com.github.urmichm.placesearchktx.network.Network
import com.github.urmichm.placesearchktx.priceNotInRange
import com.github.urmichm.placesearchktx.toRequestString
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import kotlinx.coroutines.Deferred
import kotlin.Exception

/**
 * Text Search Google API
 * @details https://developers.google.com/maps/documentation/places/web-service/search-text
 * @param builder The [Builder] object
 * */
class TextSearch private constructor(private val builder: Builder){

    private val query :String = builder.getQuery()
    private val language :String? = builder.getLanguage()
    private val location : String? = builder.getLocation()
    private var maxPrice :Int? = builder.getMaxPrice()
    private var minPrice :Int? = builder.getMinPrice()
    private var openNow :Boolean? = builder.getOpenNow()
    private var pageToken :String? = builder.getPageToken()
    private var radius :Int? = builder.getRadius()
    private var region :String? = builder.getRegion()
    private var type : Place.Type? = builder.getType()


    class Builder() {

        /**
         * The text string on which to search, for example: "restaurant" or "123 Main Street".
         * This must a place name, address, or category of establishments.
         * Any other types of input can generate errors and are not guaranteed to return valid results.
         * The Google Places service will return candidate matches based on this string and order the results based on
         * their perceived relevance.
         */
        private lateinit var query: String

        /**
         * Getter for [query]
         * @return The value of [query]
         * */
        fun getQuery() = this.query

        /**
         * Setter for [query]
         * @param query The text string on which to search
         * */
        fun setQuery(query: String): Builder = apply {
            this.query = query
        }

        /**
         * The language in which to return results.
         * */
        private var language: String? = null

        /**
         * Getter for [language]
         * @return The value of [language]
         * */
        fun getLanguage() = this.language

        /**
         * Setter for [language]
         * @param language New value for [language]
         * */
        fun setLanguage(language: String): Builder = apply {
            this.language = language
        }


        /**
         *  The [LatLng] object describing latitude/longitude around which to retrieve place information.
         * */
        private var location: String? = null

        /**
         * Getter for [location] in string format.
         * @return The value of [location]
         * */
        fun getLocation() = this.location

        /**
         * Setter for [location]
         * @param location Location provided in form of [LatLng] object
         * */
        fun setLocation(location: LatLng): Builder = apply {
            this.location = location.toRequestString()
        }


        /**
         * Restricts results to only those places within the specified range.
         * Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
         * */
        private var maxPrice: Int? = null

        /**
         * Getter for [maxPrice]
         * @return The value of [maxPrice]
         * */
        fun getMaxPrice() = this.maxPrice

        /**
         * Setter for [maxPrice]
         * @param maxPrice The new value for [maxPrice]
         * */
        fun setMaxPrice(maxPrice: Int): Builder = apply {
            this.maxPrice = maxPrice
        }


        /**
         * Restricts results to only those places within the specified range.
         * Valid values range between 0 (most affordable) to 4 (most expensive), inclusive.
         * */
        private var minPrice: Int? = null

        /**
         * Getter for [minPrice]
         * @return The value of [minPrice]
         * */
        fun getMinPrice() = this.minPrice

        /**
         * Setter for [minPrice]
         * @param minPrice The new value for [minPrice]
         * */
        fun setMinPrice(minPrice: Int): Builder = apply {
            this.minPrice = minPrice
        }


        /**
         * Returns only those places that are open for business at the time the query is sent.
         * Places that do not specify opening hours in the Google Places database will not be returned if you include this parameter in your query.
         * */
        private var openNow: Boolean? = null

        /**
         * Getter for [openNow]
         * @return The value of [openNow]
         * */
        fun getOpenNow() = this.openNow

        /**
         * Setter for [openNow]
         * @param openNow The new value for [openNow]
         * */
        fun setOpenNow(openNow: Boolean): Builder = apply {
            this.openNow = openNow
        }


        /**
         * Returns up to 20 results from a previously run search.
         * Setting a page token parameter will execute a search with the same parameters used previously — all parameters other than pagetoken will be ignored.
         * */
        private var pageToken: String? = null

        /**
         * Getter for [pageToken]
         * @return The value of [pageToken]
         * */
        fun getPageToken() = this.pageToken

        /**
         * Setter for [pageToken]
         * @param pageToken The new value for [pageToken]
         * */
        fun setPageToken(pageToken: String): Builder = apply {
            this.pageToken = pageToken
        }


        /**
         * Defines the distance (in meters) within which to return place results.
         * Note that radius must not be included if [rankBy]=distance (described under Optional parameters below) is specified.
         * */
        private var radius: Int? = null

        /**
         * Getter for [radius]
         * @return The value of [radius]
         * */
        fun getRadius() = this.radius

        /**
         * Setter for [radius]
         * @param radius The new value for [radius]
         * */
        fun setRadius(radius: Int): Builder = apply {
            this.radius = radius
        }


        /**
         * The region code, specified as a ccTLD ("top-level domain") two-character value.
         * */
        private var region: String? = null

        /**
         * Getter for [region]
         * @return The value of [region]
         * */
        fun getRegion() = this.region

        /**
         * Setter for [region]
         * @param region The new value for [region]
         * */
        fun setRegion(region: String): Builder = apply {
            this.region = region
        }


        /**
         * Restricts the results to places matching the specified type. Only one type may be specified.
         * If more than one type is provided, all types following the first entry are ignored.
         * */
        private var type: Place.Type? = null

        /**
         * Getter for [type]
         * @return The value of [type]
         * */
        fun getType() = this.type

        /**
         * Setter for [type]
         * @param type The new value for [type]
         * */
        fun setType(type: Place.Type): Builder = apply {
            this.type = type
        }


        /**
         * The build method to create a [TextSearch] object
         * @return [TextSearch] object created according to the builder settings.
         * */
        fun build(): TextSearch {
            validate()
            return TextSearch(this)
        }

        /**
         * Validate parameters before calling the server
         * @throws IllegalArgumentException if NearbySearch object is not valid
         * */
        private fun validate(){
            minPrice?.apply {
                if( priceNotInRange(this) )
                    throw IllegalArgumentException("minPrice is out of possible range.")
            }
            maxPrice?.apply {
                if( priceNotInRange(this) )
                    throw IllegalArgumentException("maxPrice is out of possible range.")
            }
            region?.apply {
                if(this.length > 2)
                    throw IllegalArgumentException("The region code, must be specified as a ccTLD (\"top-level domain\") TWO-character value.")
            }
        }
    }

    /**
     * Make a call to Text Search.
     * @return [TextSearchContainer] container object on success, null otherwise
     * */
    suspend fun call() : TextSearchContainer?{
        val textSearch : Deferred<TextSearchContainer> =
            Network.service.textSearch(
                query = query,
                language = language,
                location = location,
                maxPrice = maxPrice,
                minPrice = minPrice,
                openNow = openNow,
                pageToken = pageToken,
                radius = radius,
                region = region,
                type = type
            )

        return try{
            textSearch.await()
        } catch (e :Exception){
            println("Exception: $e");
            null
        }
    }

    companion object{
        public const val TAG = "TextSearch"
    }

}