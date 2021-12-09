package com.github.urmichm.diana.containers


/**
 * @brief Container fro nearBySearch request.
 * */
data class NearbySearchContainer(
    val status : String,
    val next_page_token : String?,
    val results :List<PlaceDetailsContainer>,
    val error_message : String?
) {

    override fun toString(): String {
        var str = "status: $status"
        if(error_message != null) {
            str += " $error_message"
        }
        return str
    }
}

