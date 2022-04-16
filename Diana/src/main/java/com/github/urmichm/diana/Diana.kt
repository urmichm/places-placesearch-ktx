package com.github.urmichm.diana

import android.util.Log

/**
 * Main class where all intialization takes place
 * */
class Diana private constructor(
    builder : Builder,
    internal val key: String
){

    init{
        vicinityAsAddress = builder.getVicinityAsAddress()
    }

    companion object {
        private const val TAG = "Diana"

        /**
         * Output format; indicates output in JavaScript Object Notation (JSON)
         * */
        const val OUTPUT_FORMAT = "json"

        /**
         * Convert vicinity data into Address data when converting Container objects into Google's Place object
         * */
        var vicinityAsAddress = true

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
         * Convert vicinity data into Address data when converting Container objects into Google's Place object
         * */
        private var vicinityAsAddress = true
        fun getVicinityAsAddress() = this.vicinityAsAddress
        fun setVicinityAsAddress(vicinity2Address : Boolean) = apply{this.vicinityAsAddress = vicinity2Address}

        /**
         * The build method to create a [Diana] object
         * @return [Diana] object created according to the builder settings.
         * */
        fun build() : Diana{
            return Diana(this, key)
        }
    }

    /**
     * Rankby enumeration for rankby parameter
     * */
    enum class Rankby(s: String) {
        PROMINENCE ("prominence"),
        DISTANCE("distance")
    }


}