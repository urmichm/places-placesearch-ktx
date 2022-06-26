package com.urmich.android.placesearchktx.containers.common

import com.squareup.moshi.Json

/**
 * Container for open_now field
 * */
data class OpeningHoursContainer (
    @Json(name="open_now")
    val openNow : Boolean?
)
