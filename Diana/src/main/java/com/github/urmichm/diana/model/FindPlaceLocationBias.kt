package com.github.urmichm.diana.model

import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.RectangularBounds


/**
 * TODO: Add proper comments to constructors!
 * Prefer results in a specified area, by specifying either a radius plus lat/lng,
 * or two lat/lng pairs representing the points of a rectangle.
 * If this parameter is not specified, the API uses IP address biasing by default.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-find-place#locationbias
 * */
class FindPlaceLocationBias {

    private enum class Type{
        POINT, CIRCULAR, RECTANGULAR
    }

    private val type : Type

    private val var0 : LatLng?
    private val var1 : LatLng?
    private val var2 : Double?


    /**
     * Point: A single lat/lng coordinate.
     * Uses the following format: point:lat,lng.
     * @param point - A single lat/lng coordinate.
     */
    constructor(point : LatLng){
        type = Type.POINT
        var0 = point
        var1 = null
        var2 = null
    }

    /**
     * Circular: A string specifying radius in meters, plus lat/lng in decimal degrees.
     * Uses the following format: circle:radius@lat,lng.
     * @param radius A string specifying radius in meters
     * @param center The center of the circle, lat/lng in decimal degrees.
     */
    constructor(radius : Double, center : LatLng){
        type = Type.CIRCULAR
        var0 = center
        var1 = null
        var2 = radius
    }

    /**
     * Rectangular: A string specifying two lat/lng pairs in decimal degrees,
     * representing the south/west and north/east points of a rectangle.
     * Uses the following format:rectangle:south,west|north,east.
     * Note that east/west values are wrapped to the range -180, 180, and north/south values are clamped to the range -90, 90.
     */
    constructor(southwest : LatLng, northeast : LatLng){
        type = Type.RECTANGULAR
        var0 = southwest
        var1 = northeast
        var2 = null
    }

    /**
     * Rectangular: A string specifying two lat/lng pairs in decimal degrees,
     * representing the south/west and north/east points of a rectangle.
     * Uses the following format:rectangle:south,west|north,east.
     * Note that east/west values are wrapped to the range -180, 180, and north/south values are clamped to the range -90, 90.
     */
    constructor(bounds : RectangularBounds){
        type = Type.RECTANGULAR
        var0 = bounds.southwest
        var1 = bounds.northeast
        var2 = null
    }


    override fun toString(): String {
        return when(type){
            Type.POINT -> "point:${var0?.latitude},${var0?.longitude}"
            Type.CIRCULAR -> "circle:${var2}@${var0?.latitude},${var0?.longitude}"
            Type.RECTANGULAR -> "rectangle:${var0?.latitude},${var0?.longitude}|${var1?.latitude},${var1?.longitude}"
        }
    }

}