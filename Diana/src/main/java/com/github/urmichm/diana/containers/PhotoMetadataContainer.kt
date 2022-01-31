package com.github.urmichm.diana.containers

import com.google.android.libraries.places.api.model.PhotoMetadata
import com.squareup.moshi.Json

data class PhotoMetadataContainer (
    @Json(name="height") val height : Int,
    @Json(name="html_attributions") val attributions : List<String>,
    @Json(name="photo_reference") val photoReference : String,
    @Json(name="width") val width : Int
    ) {

    /**
     * @brief Convert into [PhotoMetadata]
     * TODO: set attributions
     * */
    fun toPhotoMetadata() :PhotoMetadata{
        return PhotoMetadata.builder(this.photoReference)
            .setHeight(this.height)
            .setWidth(this.width)
            .setAttributions(this.attributions[0])
            .build()
    }

}
