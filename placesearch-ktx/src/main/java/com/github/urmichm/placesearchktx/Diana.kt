package com.github.urmichm.placesearchktx

import android.util.Log

/**
 * Main class where all intialization takes place
 *
 *
 * Place Search requests return a subset of the fields that are returned by Place Details requests.
 * If the field you want is not returned by Place Search, you can use Place Search to get a place_id,
 * then use that Place ID to make a Place Details request.
 * */
class Diana (
    internal val key: String
)