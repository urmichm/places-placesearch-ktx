package com.github.urmichm.placesearchktx

import com.google.android.libraries.places.api.model.Place

/**
 * Function checks if the price level is in the range of allowed values.
 * @param price Price level to be checked against possible values.
 * @return true if price level is allowed, false otherwise.
 * */
fun priceInRange(price : Int) :Boolean =
    (Place.PRICE_LEVEL_MIN_VALUE <= price && price <= Place.PRICE_LEVEL_MAX_VALUE )

/**
 * Function checks if the price level is NOT in the range of allowed values.
 * @param price Price level to be checked against possible values.
 * @return false if price level is allowed, true otherwise.
 * */
fun priceNotInRange(price : Int) :Boolean =
    !priceInRange(price)
