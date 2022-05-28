package com.github.urmichm.diana

import org.junit.Assert.*

import org.junit.Test

class CommonsKtTest {

    @Test
    fun priceInRange() {
        assertFalse( com.github.urmichm.diana.priceInRange(-1) )

        assertTrue( com.github.urmichm.diana.priceInRange(0) )
        assertTrue( com.github.urmichm.diana.priceInRange(1) )
        assertTrue( com.github.urmichm.diana.priceInRange(2) )
        assertTrue( com.github.urmichm.diana.priceInRange(3) )
        assertTrue( com.github.urmichm.diana.priceInRange(4) )

        assertFalse( com.github.urmichm.diana.priceInRange(5) )
        assertFalse( com.github.urmichm.diana.priceInRange(6) )
    }

    @Test
    fun priceNotInRange() {
        assertTrue( com.github.urmichm.diana.priceNotInRange(-1) )

        assertFalse( com.github.urmichm.diana.priceNotInRange(0) )
        assertFalse( com.github.urmichm.diana.priceNotInRange(1) )
        assertFalse( com.github.urmichm.diana.priceNotInRange(2) )
        assertFalse( com.github.urmichm.diana.priceNotInRange(3) )
        assertFalse( com.github.urmichm.diana.priceNotInRange(4) )

        assertTrue( com.github.urmichm.diana.priceNotInRange(5) )
        assertTrue( com.github.urmichm.diana.priceNotInRange(6) )
    }
}