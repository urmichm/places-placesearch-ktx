package com.github.urmichm.placesearchktx

import android.util.Log

/**
 * Main class where all intialization takes place
 *
 *
 * Place Search requests return a subset of the fields that are returned by Place Details requests.
 * If the field you want is not returned by Place Search, you can use Place Search to get a place_id,
 * then use that Place ID to make a Place Details request.
 * */
class Diana private constructor(
    builder : Builder,
    internal val key: String
){


    companion object {
        private const val TAG = "PlaceSearch-KTX"

        fun hello(){
            Log.i(TAG, "Hello Diana!")
        }

        fun bye(){
            Log.i(TAG, "Bye Diana!")
        }
    }

    /**
     * The builder class for [Diana] class
     * @param key Your application's API key. This key identifies your application.
     * */
    class Builder(private val key : String){

        /**
         * The build method to create a [Diana] object
         * @return [Diana] object created according to the builder settings.
         * */
        fun build() : Diana{
            return Diana(this, key)
        }
    }


}