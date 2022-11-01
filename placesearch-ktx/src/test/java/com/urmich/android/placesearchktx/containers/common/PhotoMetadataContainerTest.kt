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