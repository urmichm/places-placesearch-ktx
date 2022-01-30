package com.github.urmichm.diana.containers

import org.junit.Assert.*
import org.junit.Test

class PlusCodeContainerTest {
    @Test
    fun toPlusCodeNonNullParams() {
        val container = PlusCodeContainer("global", "compound")
        val plusCode = container.toPlusCode()

        assertEquals(container.globalCode, plusCode.globalCode)
        assertEquals(container.compoundCode, plusCode.compoundCode)
    }

    @Test
    fun toPlusCodeNullParam() {
        val container = PlusCodeContainer("global", null)
        val plusCode = container.toPlusCode()

        assertEquals(container.globalCode, plusCode.globalCode)
        assertEquals(container.compoundCode, plusCode.compoundCode)
    }
}