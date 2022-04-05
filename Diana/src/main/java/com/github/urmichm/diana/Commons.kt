package com.github.urmichm.diana

import com.google.android.libraries.places.api.model.Place

fun priceInRange(price : Int) :Boolean =
    (Place.PRICE_LEVEL_MIN_VALUE <= price && price <= Place.PRICE_LEVEL_MAX_VALUE )

fun priceNotInRange(price : Int) :Boolean =
    !priceInRange(price)
