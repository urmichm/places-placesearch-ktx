package com.github.urmichm.diana

import android.util.Log
import com.github.urmichm.diana.containers.PlacesNearbySearchContainer
import com.github.urmichm.diana.network.Network
import kotlinx.coroutines.Deferred
import java.lang.Exception

class Diana private constructor(
    builder : Builder,
    private val key: String
){


    init{
        vicinity2Address = builder.getVicinity2Address()
    }

    companion object {
        private const val TAG = "Diana"
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

    /**
     * @brief makes a call to Nearby Search
     * TODO: add parameter validation
     * */
    suspend fun nearbySearch(type :String, latLng :String, rankby :String) : PlacesNearbySearchContainer? {
        val nearby: Deferred<PlacesNearbySearchContainer> =
            Network.diana.nearbySearch(key,latLng, type, rankby)

        return try {
            val response = nearby.await()
            response
        } catch (e: Exception) {
            println("Exceptioin e: $e");
            null
        }
    }

}