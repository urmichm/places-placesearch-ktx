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
        vicinity2Address = builder.getVicinity2Address()
    }

    companion object {
        private const val TAG = "Diana"
        const val OUTPUT_FORMAT = "json"
        var vicinity2Address = true

        fun hello(){
            Log.i(TAG, "Hello Diana!")
        }

        fun bye(){
            Log.i(TAG, "Bye Diana!")
        }
    }

    class Builder(private val key : String){

        private var vicinity2Address = true

        fun getVicinity2Address() = this.vicinity2Address
        fun setVicinity2Address(vicinity2Address : Boolean) = apply{this.vicinity2Address = vicinity2Address}

        fun build() : Diana{
            return Diana(this, key)
        }
    }

    enum class Rankby(s: String) {
        PROMINENCE ("prominence"),
        DISTANCE("distance")
    }


}