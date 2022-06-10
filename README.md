[![](https://jitpack.io/v/urmichm/diana-android.svg)](https://jitpack.io/#urmichm/diana-android)

# Place Search extension lirbrary for Places SDK
## Place Search | Places API Library

_**Usage.**_

In order to use the library please include the following dependency
```gradle
dependencies {
  implementation 'com.github.urmichm:
places-placesearch-ktx:RELEASE_TAG'
 }
```

If you have not included the jitpack repository in your project plese add the following in your root build.gradle at the end of repositories
```gradle
allprojects {
  repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

The main goal of the library is to call [Google Place Search API](https://developers.google.com/maps/documentation/places/web-service/search#PlaceSearchRequests)

The result of the call may be converted into a set of [Place](https://developers.google.com/maps/documentation/places/android-sdk/reference/com/google/android/libraries/places/api/model/Place)
objects.

Such conversion accelerates the usage of Place Search and integrate the package into Place Details SDK

## External Libraries required
* Retrofit
* Moshi
* Place Details SDK

