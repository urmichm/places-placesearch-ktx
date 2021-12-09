package com.github.urmichm.diana.containers

/**
 * @brief Container for Place object.
 * @details https://developers.google.com/maps/documentation/places/web-service/search-nearby#Place
 * */
data class PlaceDetailsContainer(
    val place_id: String,
    val name :String,
    val rating :Float?,
    val user_ratings_total :Int?,
    val vicinity :String,
    val types :List<String>?,
    val geometry : GeometryContainer,
    val icon :String?
) {
    override fun toString(): String {
        return "${this.name} of type ${this.types} ${this.rating}"
    }
}