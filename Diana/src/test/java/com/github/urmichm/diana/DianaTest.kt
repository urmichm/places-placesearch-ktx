package com.github.urmichm.diana

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.junit.Assert.*

import org.junit.Test

class DianaTest {

    private val JsonFormat = "json"

    @Test
    fun testDianaKey(){
        val key = "my-key"
        val d = Diana.Builder(key).build()
        assertEquals(key,d.key)
    }

    @Test
    fun testVicinityAsAddress(){
        // given default value
        assertTrue(Diana.vicinityAsAddress)

        // override default value
        Diana.vicinityAsAddress = false
        assertFalse(Diana.vicinityAsAddress)

        // restore the default value
        Diana.vicinityAsAddress = true
        assertTrue(Diana.vicinityAsAddress)
    }

    @Test
    fun defaultOutputFormat(){
        assertEquals(JsonFormat, Diana.OUTPUT_FORMAT)
    }

//    @Test
//    fun nearbySearch() {
//        val PLACES_API_KEY : String = YOUR_KEY
//        var complete = false
//
//        val type   = "tourist_attraction"
//        val latLng = "52.249787,21.012575"
//        val rankby = "distance"
//
//        val diana = Diana.Builder(PLACES_API_KEY)
//            .setVicinity2Address(true)
//            .build()
//
//        CoroutineScope(Dispatchers.IO).launch{
//            val response = diana.nearbySearch(type, latLng, rankby)
//            response?.let {
//                assertEquals("OK",  response.status)
//                val p = response.results[0].asPlace()
//
////                println("All results:\n${response.results}")
////                println(p)
//
//                assertEquals(response.results[0].name, p.name)
//                complete = true
//            }
//        }
//
//        Thread.sleep(5_000)
//        assertTrue(complete)
//    }
}