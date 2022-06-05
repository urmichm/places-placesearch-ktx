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

    @Test
    fun maxPriceOutOfRange(){
        val textSearch = TextSearch.Builder(d)
            .setQuery("query")
            .setMaxPrice(6)

        assertThrows("maxPrice is out of possible range.", IllegalArgumentException::class.java) {
            textSearch.build()
        }
    }

    @Test
    fun minPriceOutOfRange(){
        val textSearch = TextSearch.Builder(d)
            .setQuery("query")
            .setMinPrice(-1)

        assertThrows("minPrice is out of possible range.", IllegalArgumentException::class.java) {
            textSearch.build()
        }
    }

    @Test
    fun regionIsIncorrect(){
        val textSearch = TextSearch.Builder(d)
            .setQuery("query")
            .setRegion("pll")

        assertThrows("The region code, must be specified as a ccTLD (\"top-level domain\") TWO-character value.", IllegalArgumentException::class.java) {
            textSearch.build()
        }
    }

}