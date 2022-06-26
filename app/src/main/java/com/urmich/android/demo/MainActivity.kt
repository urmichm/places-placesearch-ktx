package com.urmich.android.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.urmich.android.placesearchktx.placesearch.search.FindPlace
import com.urmich.android.placesearchktx.placesearch.search.NearbySearch
import com.urmich.android.placesearchktx.placesearch.search.TextSearch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("MainActivity" , FindPlace.TAG)
        Log.i("MainActivity" , NearbySearch.TAG)
        Log.i("MainActivity" , TextSearch.TAG)

        setContentView(R.layout.activity_main)
    }
}