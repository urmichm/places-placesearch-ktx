package com.urmich.android.placesearchktx.placesearch

import com.urmich.android.placesearchktx.containers.PlaceSearchContainer

abstract class PlaceSearch {

    abstract suspend fun call() : PlaceSearchContainer?

}
