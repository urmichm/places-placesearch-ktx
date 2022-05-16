package com.github.urmichm.diana.placesearch

import com.github.urmichm.diana.Diana
import com.github.urmichm.diana.containers.PlacesFindPlaceContainer
import com.github.urmichm.diana.network.Network
import com.github.urmichm.diana.toRequestString
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
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
    private val input :String = builder.getInput()

    /**
     * The required parameter.
     * The type of input. This can be one of either [textquery] or [phonenumber].
     * Phone numbers must be in international format (prefixed by a plus sign ("+"),
     * followed by the country code, then the phone number itself).
     * See E.164 ITU recommendation for more information.
     * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#inputtype
     * */
    private val inputtype :String = builder.getInputType().toString()

    /**
     * Use the fields parameter to specify a comma-separated list of place data types to return.
     * For example: fields=formatted_address,name,geometry.
     * Use a forward slash when specifying compound values. For example: opening_hours/open_now.
     * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#fields
     * */
    private var fields :String? = builder.getFields()

    /**
     * The language in which to return results.
     * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#language
     * */
    private var language :String? = builder.getLanguage()

    /**
     * Prefer results in a specified area, by specifying either a radius plus lat/lng, or two lat/lng pairs representing the points of a rectangle. If this parameter is not specified, the API uses IP address biasing by default.
     * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#locationbias
     * */
    private var locationbias :String? = builder.getLocationBias()


    /**
     * The builder class for [FindPlace] class
     * @param builder The [Builder] object
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
        private lateinit var input :String

        /**
         * Getter for [input]
         * @return The value of [input]
         * */
        fun getInput() = input

        /**
         * Getter for [input]
         * @param input The text string on which to search, for example: "restaurant" or "123 Main Street".
         * */
        fun setInput(input :String) :Builder = apply {
            this.input = input
        }

        /**
         * The required parameter.
         * The type of input. This can be one of either [textquery] or [phonenumber].
         * Phone numbers must be in international format (prefixed by a plus sign ("+"),
         * followed by the country code, then the phone number itself).
         * See E.164 ITU recommendation for more information.
         * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#inputtype
         * */
        private lateinit var inputtype :InputType

        /**
         * Getter for [inputtype]
         * @return The value of [inputtype]
         * */
        fun getInputType() = inputtype

        /**
         * Setter for [inputtype]
         * @param inputType The type of input. This can be one of either [InputType.TEXTQUERY] or [InputType.PHONENUMBER].
         * */
        fun setInputType(inputType: InputType) :Builder = apply {
            this.inputtype = inputType
        }

        /**
         * Uses the fields parameter to specify a comma-separated list of place data types to return.
         * For example: fields=formatted_address,name,geometry.
         * Uses a forward slash when specifying compound values. For example: opening_hours/open_now.
         * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#fields
         * */
        private var fields :String? = null

        /**
         * Getter for [fields]
         * @return The value of [fields]
         * */
        fun getFields() = fields

        /**
         * Setter for [fields]
         * @param fields List of [FindPlace.Field] enums to specify a list of place data types to return.
         * */
        fun setFields(fields : List<FindPlace.Field>): Builder = apply {
            this.fields = fields.joinToString(separator = ",")
        }

        /**
         * Setter for [fields]
         * @param fields List of [Place.Field] enums to specify a list of place data types to return.
         * */
        @JvmName("googleSetFields")
        fun setFields(fields : List<Place.Field>) : Builder = apply {
            this.fields = fields.joinToString(separator = ",", transform = {
                it.toRequestString()
            })
        }


        /**
         * The language in which to return results.
         * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#language
         * */
        private var language :String? = null

        /**
         * Getter for [language]
         * @return The value of [language]
         * */
        fun getLanguage() = language

        /**
         * Setter for [language]
         * @param language value ti be set
         * */
        fun setLanguage(language :String) :Builder = apply {
            this.language = language
        }

        /**
         * Prefer results in a specified area, by specifying either a radius plus lat/lng, or two lat/lng pairs representing the points of a rectangle. If this parameter is not specified, the API uses IP address biasing by default.
         * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#locationbias
         * */
        private var locationbias : String? = null

        /**
         * Getter for [locationbias]
         * @return The value of [locationbias]
         * */
        fun getLocationBias() = locationbias

        /**
         * Circular: A [locationbias] specifying radius in meters, plus lat/lng in decimal degrees.
         * Uses the following format: circle:radius@lat,lng.
         * @param radius A string specifying radius in meters
         * @param center The center of the circle, lat/lng in decimal degrees.
         */
        fun setLocationBias(radius : Double, center : LatLng) :Builder = apply {
            locationbias =
                "circle:${radius}@${center.latitude},${center.longitude}"
        }

        /**
         * Point: A [locationbias] as a single lat/lng coordinate.
         * Uses the following format: point:lat,lng.
         * @param point - A single lat/lng coordinate.
         */
        fun setLocationBias(point : LatLng) :Builder = apply {
            locationbias =
                "point:${point.latitude},${point.longitude}"
        }

        /**
         * Rectangular: A [locationbias] specifying two lat/lng pairs in decimal degrees,
         * representing the south/west and north/east points of a rectangle.
         * Uses the following format:rectangle:south,west|north,east.
         * Note that east/west values are wrapped to the range -180, 180, and north/south values are clamped to the range -90, 90.
         */
        fun setLocationBias(southwest : LatLng, northeast : LatLng) :Builder = apply{
            locationbias =
                "rectangle:${southwest.latitude},${southwest.longitude}|${northeast.latitude},${northeast.longitude}"
        }

        /**
         * Rectangular: A [locationbias] specifying two lat/lng pairs in decimal degrees,
         * representing the south/west and north/east points of a rectangle.
         * Uses the following format:rectangle:south,west|north,east.
         * Note that east/west values are wrapped to the range -180, 180, and north/south values are clamped to the range -90, 90.
         */
        fun setLocationBias(bounds : RectangularBounds) :Builder = apply{
            locationbias =
                "rectangle:${bounds.southwest.latitude},${bounds.southwest.longitude}" +
                        "|${bounds.northeast.latitude},${bounds.northeast.longitude}"
        }


        /**
         * The build method to create a [FindPlace] object
         * @return [FindPlace] object created according to the builder settings.
         * */
        fun build() : FindPlace = FindPlace(this)
    }

    /**
     * The type of input. This can be one of either [TEXTQUERY] or [PHONENUMBER].
     * Phone numbers must be in international format (prefixed by a plus sign ("+"),
     * followed by the country code, then the phone number itself).
     * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#inputtype
     * */
    enum class InputType {
        TEXTQUERY, PHONENUMBER;

        override fun toString(): String {
            return super.toString().lowercase()
        }
    }

    /**
     * Place data fields define the types of Place data to return when requesting Place Search
     * @details https://developers.google.com/maps/documentation/places/android-sdk/place-data-fields
     */
    enum class Field{

        // TODO: https://developers.google.com/maps/documentation/places/web-service/place-data-fields#places-api-fields-support
        // TODO: no ADDRESS_COMPONENT, ADR_ADDRESS, INTERNATIONAL_PHONE_NUMBER, WEBSITE, URL, UTC_OFFSET, VICINITY,FORMATTED_PHONE_NUMBER, REVIEWS

        // Basic Data
        ADDRESS_COMPONENT,      ADR_ADDRESS,    BUSINESS_STATUS,
        FORMATTED_ADDRESS,      GEOMETRY,       VIEWPORT,
        LOCATION,               ICON,           ICON_MASK_BASE_URI,
        ICON_BACKGROUND_COLOR,  NAME,           PERMANENTLY_CLOSED,
        PHOTOS,                 PLACE_ID,       PLUS_CODE,
        TYPE,                   URL,            UTC_OFFSET,
        VICINITY,

        // Contact Data Fields
        FORMATTED_PHONE_NUMBER,      INTERNATIONAL_PHONE_NUMBER,
        OPENING_HOURS,               OPEN_NOW,
        WEBSITE,

        // Atmosphere Data Fields
        PRICE_LEVEL,        RATING,
        REVIEWS,            USER_RATINGS_TOTAL;

        override fun toString(): String {
            return when(this){
                VIEWPORT -> "geometry/viewport"
                LOCATION -> "geometry/location"
                OPEN_NOW -> "opening_hours/open_now"
                else -> super.toString().lowercase()
            }
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