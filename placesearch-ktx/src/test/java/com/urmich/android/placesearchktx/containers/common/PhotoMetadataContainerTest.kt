package com.urmich.android.placesearchktx.containers.common

import org.junit.Test
import org.junit.Assert.*


class PhotoMetadataContainerTest {

    @Test
    fun toPhotoMetadata() {
        val container = PhotoMetadataContainer(
            height = 3000,
            width = 4000,
            attributions = listOf("<a href=\"https://maps.google.com/maps/contrib/105140283592287950266\">A Google User</a>"),
            photoReference = "Aap_uED3eK9pZ7OdnCwzuFyujDnzBmiHYvkJL-rVHc_d3T9sAMlayfQkIepkqnrdM8dA_mYnr_RgQ-N9_9SNCPcDYijx2dfnTQdzM2Pzn4LdNhSLH1D27Go_mucZo3znVqxpU2Xx7HqgHk82JQM9fN-Irte2gAEvMWn1J8IjXjqjZ6AVLhJl"
        )

        val photoMetadata = container.toPhotoMetadata()

        assertNotNull(photoMetadata)
        assertEquals(container.height, photoMetadata.height)
        assertEquals(container.width, photoMetadata.width)
        assertEquals(container.attributions[0], photoMetadata.attributions)
    }
}