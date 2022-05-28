package com.github.urmichm.placesearchktx

/**
 * Message class to be return after validation of the request parameters.
 * @param message Message of the validation
 * @param isValid true if the validation was successful; false otherwise
 * */
class Message(val message : String, val isValid : Boolean )