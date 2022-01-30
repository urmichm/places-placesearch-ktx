package com.github.urmichm.diana.containers

import com.squareup.moshi.Json

data class OpeningHoursContainer (
    @Json(name="open_now")
    val openNow : Boolean?
)
