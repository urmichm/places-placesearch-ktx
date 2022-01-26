# Notes

## [Places API fields support](https://developers.google.com/maps/documentation/places/web-service/place-data-fields#places-api-fields-support)
Place Search, Nearby Search, and Text Search requests all return a subset of the fields that are returned by Place Details requests. These methods do NOT return the following fields:

* address_component
* adr_address
* formatted_phone_number
* international_phone_number
* reviews
* type
* url
* utc_offset
* vicinity (?)
* website

To return one or more of these data fields for a place, make a Place Details request, pass a place ID, and specify which fields to return.