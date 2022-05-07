package com.github.urmichm.diana.placesearch

import com.github.urmichm.diana.Diana
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class FindPlaceTest {

    private lateinit var d : Diana

    @Before
    fun initDiana(){
        d = Diana.Builder("google-key").build()
    }

    @Test
    fun findPlaceBuiltSuccessfully() {
        val findPlace = FindPlace.Builder(d).apply {
            input = "some-input"
            inputtype = "textquery"
        }
            .build()

        assertNotNull(findPlace)
    }

    @Test
    fun findPlaceBuildFailedInputNotProvided() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            FindPlace.Builder(d).apply {
                inputtype = "textquery"
            }
                .build()
        }
    }

    @Test
    fun findPlaceBuildFailedInputTypeNotProvided() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            FindPlace.Builder(d).apply {
                input = "input"
            }
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