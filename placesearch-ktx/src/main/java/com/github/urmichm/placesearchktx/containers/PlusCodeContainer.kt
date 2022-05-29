package com.github.urmichm.placesearchktx.containers

import com.google.android.libraries.places.api.model.PlusCode
import com.squareup.moshi.Json

/**
 * Container for a plus code field.
 * */
data class PlusCodeContainer(
    @Json(name="global_code") val globalCode : String,
    @Json(name="compound_code") val compoundCode :String?
            ){

    /**
     * Convert [PlusCodeContainer] object into [PlusCode] object.
     * */
    fun toPlusCode():PlusCode{
        return PlusCode.builder()
            .setGlobalCode(this.globalCode)
            .setCompoundCode(this.compoundCode)
            .build()
    }

}
