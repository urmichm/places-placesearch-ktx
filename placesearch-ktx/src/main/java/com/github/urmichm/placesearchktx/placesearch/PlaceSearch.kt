package com.github.urmichm.placesearchktx.placesearch

import com.github.urmichm.placesearchktx.containers.PlaceSearchContainer

abstract class PlaceSearch {

    abstract suspend fun call() : PlaceSearchContainer?

}
