package com.github.urmichm.placesearchktx

import org.junit.Assert.*

import org.junit.Test

class DianaTest {

    @Test
    fun testDianaKey(){
        val key = "my-key"
        val d = Diana(key)
        assertEquals(key,d.key)
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