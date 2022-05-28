package com.github.urmichm.placesearchktx

import org.junit.Assert.*

import org.junit.Test

class CommonsKtTest {

    @Test
    fun priceInRange() {
        assertFalse( com.github.urmichm.placesearchktx.priceInRange(-1) )

        assertTrue( com.github.urmichm.placesearchktx.priceInRange(0) )
        assertTrue( com.github.urmichm.placesearchktx.priceInRange(1) )
        assertTrue( com.github.urmichm.placesearchktx.priceInRange(2) )
        assertTrue( com.github.urmichm.placesearchktx.priceInRange(3) )
        assertTrue( com.github.urmichm.placesearchktx.priceInRange(4) )

        assertFalse( com.github.urmichm.placesearchktx.priceInRange(5) )
        assertFalse( com.github.urmichm.placesearchktx.priceInRange(6) )
    }

    @Test
    fun priceNotInRange() {
        assertTrue( com.github.urmichm.placesearchktx.priceNotInRange(-1) )

        assertFalse( com.github.urmichm.placesearchktx.priceNotInRange(0) )
        assertFalse( com.github.urmichm.placesearchktx.priceNotInRange(1) )
        assertFalse( com.github.urmichm.placesearchktx.priceNotInRange(2) )
        assertFalse( com.github.urmichm.placesearchktx.priceNotInRange(3) )
        assertFalse( com.github.urmichm.placesearchktx.priceNotInRange(4) )

        assertTrue( com.github.urmichm.placesearchktx.priceNotInRange(5) )
        assertTrue( com.github.urmichm.placesearchktx.priceNotInRange(6) )
    }
}