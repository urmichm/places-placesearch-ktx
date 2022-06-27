package com.urmich.android.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.Place
import com.urmich.android.demo.databinding.ActivityMapsBinding
import com.urmich.android.placesearchktx.placesearch.PlaceSearch
import com.urmich.android.placesearchktx.placesearch.search.FindPlace
import com.urmich.android.placesearchktx.placesearch.search.NearbySearch
import com.urmich.android.placesearchktx.placesearch.search.TextSearch
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val TAG = "MapsActivity"
    private val homeLatLng = LatLng(52.247551,21.0126315)
    private val zoomLevel = 15f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))

        mMap.setOnMapClickListener {
            mMap.clear()
        }
    }

    /**
     * Simple example of Find Place usage
     * */
    private fun runFindPlace(){
        val findPlace = FindPlace
            .Builder()
                .setInput("royal castle")
                .setInputType(FindPlace.InputType.TEXTQUERY)
                .setLocationBias(homeLatLng)
                .setFields(listOf(FindPlace.Field.NAME, FindPlace.Field.LOCATION))
                .setLanguage("en")
            .build()
        makeAsyncCall(findPlace)
    }

    /**
     * Simple example of Nearby Search usage
     * */
    private fun runNearbySearch(){
        val nearbySearch = NearbySearch
            .Builder()
                .setType(Place.Type.TOURIST_ATTRACTION)
                .setRadius(5000)
                .setLocation(homeLatLng)
            .build()
        makeAsyncCall(nearbySearch)
    }

    /**
     * Simple example of Text Search usage
     * */
    private fun runTextSearch(){
        val textSearch = TextSearch
            .Builder()
                .setQuery("restaurants in Warsaw")
                .setLanguage("en")
            .build()
        makeAsyncCall(textSearch)
    }

    private fun makeAsyncCall(placeSearch: PlaceSearch) {
        lifecycleScope.launch {
            val response = placeSearch.call()
            if(response?.status == "OK"){
                response.places.forEach { it ->
                    mMap.addMarker(
                        MarkerOptions()
                            .position(it.asPlace().latLng!!)
                            .title(it.asPlace().name)
                    )
                }
            }
            else{
                Log.i(TAG, response.toString())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mMap.clear()
        return when (item.itemId) {
            R.id.find_place -> {
                runFindPlace()
                Log.i(TAG, FindPlace.TAG)
                true
            }
            R.id.nearby_search -> {
                runNearbySearch()
                Log.i(TAG, NearbySearch.TAG)
                true
            }
            R.id.text_search -> {
                runTextSearch()
                Log.i(TAG, TextSearch.TAG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
