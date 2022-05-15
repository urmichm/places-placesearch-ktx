package com.github.urmichm.diana.placesearch

import com.github.urmichm.diana.Diana
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class FindPlaceTest {

    private lateinit var d : Diana

    @Before
    fun initDiana(){
        d = Diana.Builder("YOUR_API_KEY").build()
    }

    @Test
    fun findPlaceBuiltSuccessfully() {
        val findPlace = FindPlace.Builder(d)
            .setInput("input")
            .setInputType(FindPlace.InputType.TEXTQUERY)
            .build()

        assertNotNull(findPlace)
    }

    @Test
    fun findPlaceBuildFailedInputNotProvided() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            FindPlace.Builder(d)
                .setInputType(FindPlace.InputType.TEXTQUERY)
                .build()
        }
    }

    @Test
    fun findPlaceBuildFailedInputTypeNotProvided() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            FindPlace.Builder(d)
                .setInput("input")
                .build()
        }
    }

    @Test
    fun findPlaceBuildFailed() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            FindPlace.Builder(d)
                .build()
        }
    }
}