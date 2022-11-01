// Copyright 2020 Urmich Mikhail
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

package com.urmich.android.placesearchktx

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

import org.junit.Test

class CommonsKtTest {

    @Test
    fun priceInRange() {
        assertFalse( priceInRange(-1) )

        assertTrue( priceInRange(0) )
        assertTrue( priceInRange(1) )
        assertTrue( priceInRange(2) )
        assertTrue( priceInRange(3) )
        assertTrue( priceInRange(4) )

        assertFalse( priceInRange(5) )
        assertFalse( priceInRange(6) )
    }

    @Test
    fun priceNotInRange() {
        assertTrue( priceNotInRange(-1) )

        assertFalse( priceNotInRange(0) )
        assertFalse( priceNotInRange(1) )
        assertFalse( priceNotInRange(2) )
        assertFalse( priceNotInRange(3) )
        assertFalse( priceNotInRange(4) )

        assertTrue( priceNotInRange(5) )
        assertTrue( priceNotInRange(6) )
    }
}