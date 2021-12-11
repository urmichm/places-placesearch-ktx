[![](https://jitpack.io/v/urmichm/diana-android.svg)](https://jitpack.io/#urmichm/diana-android)

# Diana Library

_**Usage.**_

In order to use the library please include the following dependency
```gradle
dependencies {
  implementation 'com.github.urmichm:diana-android:0.1.2'
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

This package is responsible for calling [Google Place Search API](https://developers.google.com/maps/documentation/places/web-service/search#PlaceSearchRequests)

The result of a call shall be converted into a set of [Place](https://developers.google.com/maps/documentation/places/android-sdk/reference/com/google/android/libraries/places/api/model/Place)
objects.

Such conversion shall accelerate the calling ot Place Search and integrate the package into Place Details SDK

## External Libraries required
* Retrofit
* Moshi
* Place Details SDK

