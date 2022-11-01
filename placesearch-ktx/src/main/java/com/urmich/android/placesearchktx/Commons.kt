// Copyright 2020 Urmich Mikhail
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.urmich.android.placesearchktx

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
