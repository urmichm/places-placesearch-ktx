package com.github.urmichm.diana.placesearch

import com.github.urmichm.diana.Diana
import com.github.urmichm.diana.containers.PlacesFindPlaceContainer
import com.github.urmichm.diana.network.Network
import kotlinx.coroutines.Deferred

/**
 * Find Place Google API
 * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place
 * @param builder The [Builder] object
 * */
class FindPlace private constructor(private val builder :Builder) {

    /**
     * [Diana] object with general settings
     * */
    private val diana : Diana = builder.diana;

    /**
     * The required parameter.
     * The text string on which to search, for example: "restaurant" or "123 Main Street".
     * This must be a place name, address, or category of establishments.
     * Any other types of input can generate errors and are not guaranteed to return valid results.
     * The Places API will return candidate matches based on this string and order the results based
     * on their perceived relevance.
     * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#input
     * */
    private val input :String = builder.input

    /**
     * The required parameter.
     * The type of input. This can be one of either [textquery] or [phonenumber].
     * Phone numbers must be in international format (prefixed by a plus sign ("+"),
     * followed by the country code, then the phone number itself).
     * See E.164 ITU recommendation for more information.
     * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#inputtype
     * */
    private val inputtype :String = builder.inputtype

    /**
     * Use the fields parameter to specify a comma-separated list of place data types to return.
     * For example: fields=formatted_address,name,geometry.
     * Use a forward slash when specifying compound values. For example: opening_hours/open_now.
     * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#fields
     * */
    private var fields :String? = builder.fields

    /**
     * The language in which to return results.
     * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#language
     * */
    private var language :String? = builder.language

    /**
     * Prefer results in a specified area, by specifying either a radius plus lat/lng, or two lat/lng pairs representing the points of a rectangle. If this parameter is not specified, the API uses IP address biasing by default.
     * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#locationbias
     * */
    private var locationbias :String? = builder.locationbias


    /**
     * The builder class for [FindPlace] class
     * */
    class Builder(val diana : Diana){

        /**
         * The required parameter.
         * The text string on which to search, for example: "restaurant" or "123 Main Street".
         * This must be a place name, address, or category of establishments.
         * Any other types of input can generate errors and are not guaranteed to return valid results.
         * The Places API will return candidate matches based on this string and order the results based
         * on their perceived relevance.
         * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#input
         * */
        lateinit var input :String

        /**
         * The required parameter.
         * The type of input. This can be one of either [textquery] or [phonenumber].
         * Phone numbers must be in international format (prefixed by a plus sign ("+"),
         * followed by the country code, then the phone number itself).
         * See E.164 ITU recommendation for more information.
         * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#inputtype
         * */
        lateinit var inputtype :String

        /**
         * Use the fields parameter to specify a comma-separated list of place data types to return.
         * For example: fields=formatted_address,name,geometry.
         * Use a forward slash when specifying compound values. For example: opening_hours/open_now.
         * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#fields
         * */
        var fields :String? = null

        /**
         * The language in which to return results.
         * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#language
         * */
        var language :String? = null

        /**
         * Prefer results in a specified area, by specifying either a radius plus lat/lng, or two lat/lng pairs representing the points of a rectangle. If this parameter is not specified, the API uses IP address biasing by default.
         * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#locationbias
         * */
        var locationbias :String? = null

        /**
         * The build method to create a [FindPlace] object
         * @return [FindPlace] object created according to the builder settings.
         * */
        fun build() : FindPlace{
            return FindPlace(this)
        }
    }


    /**
     * Make a call to Find Place
     * @return [PlacesFindPlaceContainer] container object on success, null otherwise
     * */
    suspend fun call(): PlacesFindPlaceContainer?{

        val find : Deferred<PlacesFindPlaceContainer> =
            Network.diana.findPlace(
                key = diana.key,
                input = input,
                inputtype = inputtype,
                fields = fields,
                language = language,
                locationbias = locationbias
            )

        return try {
            find.await()
        } catch (e: Exception) {
            println("Exception: $e");
            null
        }
    }

}