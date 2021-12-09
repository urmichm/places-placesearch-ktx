package com.github.urmichm.diana

import android.util.Log
import com.github.urmichm.diana.containers.NearbySearchContainer
import com.github.urmichm.diana.network.Network
import kotlinx.coroutines.Deferred
import java.lang.Exception

class Diana private constructor(
    private val key : String
){

    companion object {
        private const val TAG = "Diana"


        fun Builder(key : String) : Diana{

            return Diana(key)
        }

        fun hello(){
            Log.i(TAG, "Hello Diana!")
        }

        fun bye(){
            Log.i(TAG, "Bye Diana!")
        }
    }
    /**
     * @brief makes a call to Nearby Search
     * TODO add parameter validation
     * */
    suspend fun nearbySearch(type :String, latLng :String, rankby :String) : NearbySearchContainer? {
        val nearby: Deferred<NearbySearchContainer> =
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