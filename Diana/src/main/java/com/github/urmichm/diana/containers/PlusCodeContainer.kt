package com.github.urmichm.diana.containers

import com.google.android.libraries.places.api.model.PlusCode
import com.squareup.moshi.Json

data class PlusCodeContainer(
    @Json(name="global_code") val globalCode : String,
    @Json(name="compound_code") val compoundCode :String?
            ){

    fun toPlusCode():PlusCode{
        return PlusCode.builder()
            .setGlobalCode(this.globalCode)
            .setCompoundCode(this.compoundCode)
            .build()
    }

}
