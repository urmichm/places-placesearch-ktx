// Copyright 2022 Urmich Mikhail
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.urmich.android.placesearchktx.placesearch.search

import org.junit.Assert.*
import org.junit.Test

class TextSearchTest {


    @Test
    fun queryParamNotSpecified(){
        assertThrows(UninitializedPropertyAccessException::class.java) {
            TextSearch.Builder()
                .build()
        }
    }

    @Test
    fun queryParamSpecified(){
        val textSearch = TextSearch.Builder()
            .setQuery("query")
            .build()
        assertNotNull(textSearch)
    }

    @Test
    fun maxPriceOutOfRange(){
        val textSearch = TextSearch.Builder()
            .setQuery("query")
            .setMaxPrice(6)

        assertThrows("maxPrice is out of possible range.", IllegalArgumentException::class.java) {
            textSearch.build()
        }
    }

    @Test
    fun minPriceOutOfRange(){
        val textSearch = TextSearch.Builder()
            .setQuery("query")
            .setMinPrice(-1)

        assertThrows("minPrice is out of possible range.", IllegalArgumentException::class.java) {
            textSearch.build()
        }
    }

    @Test
    fun regionIsIncorrect(){
        val textSearch = TextSearch.Builder()
            .setQuery("query")
            .setRegion("pll")

        assertThrows("The region code, must be specified as a ccTLD (\"top-level domain\") TWO-character value.", IllegalArgumentException::class.java) {
            textSearch.build()
        }
    }

}