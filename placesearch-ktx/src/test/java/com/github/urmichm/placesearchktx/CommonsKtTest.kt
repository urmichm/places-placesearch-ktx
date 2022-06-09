package com.github.urmichm.placesearchktx

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