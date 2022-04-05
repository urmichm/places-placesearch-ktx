package com.github.urmichm.diana

import com.google.android.gms.maps.model.LatLng


/**
 * @brief Convert LatLng object to String for API requests
 * @return String object of LatLng fot API requests
 * */
internal fun LatLng.toRequestString():String{
    return "${latitude},${longitude}"
}

