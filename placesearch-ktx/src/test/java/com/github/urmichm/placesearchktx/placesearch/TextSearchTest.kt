package com.github.urmichm.placesearchktx.placesearch

import com.github.urmichm.placesearchktx.Diana
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TextSearchTest {

    private lateinit var d : Diana

    @Before
    fun initDiana(){
        d = Diana.Builder("YOUR_API_KEY").build()
    }

    @Test
    fun queryParamNotSpecified(){
        assertThrows(UninitializedPropertyAccessException::class.java) {
            TextSearch.Builder(d)
                .build()
        }
    }

    @Test
    fun queryParamSpecified(){
        val textSearch = TextSearch.Builder(d)
            .setQuery("query")
            .build()
        assertNotNull(textSearch)
    }

}