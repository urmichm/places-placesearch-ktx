package com.github.urmichm.placesearchktx.containers.common

import com.google.android.libraries.places.api.model.PhotoMetadata
import com.squareup.moshi.Json

/**
 * Container for photo metadata field.
 * */
data class PhotoMetadataContainer (
    @Json(name="height") val height : Int,
    @Json(name="html_attributions") val attributions : List<String>,
    @Json(name="photo_reference") val photoReference : String,
    @Json(name="width") val width : Int
    ) {

    /**
     * Convert into [PhotoMetadata]
     * @warning method to be tested
     * */
    fun toPhotoMetadata() :PhotoMetadata{
        return PhotoMetadata.builder(this.photoReference)
            .setHeight(this.height)
            .setWidth(this.width)
            .setAttributions(this.attributions[0])
            .build()
    }

}
