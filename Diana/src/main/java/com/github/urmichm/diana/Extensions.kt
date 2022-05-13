package com.github.urmichm.diana

import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place


/**
 * Convert LatLng object to String for API requests
 * @return String object of LatLng fot API requests
 * */
internal fun LatLng.toRequestString():String{
    return "${latitude},${longitude}"
}


internal fun Place.Field.toRequestString():String{
    return when(this){

        else -> this.toString().lowercase()
    }
}

