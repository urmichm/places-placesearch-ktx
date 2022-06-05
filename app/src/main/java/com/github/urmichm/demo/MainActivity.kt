package com.github.urmichm.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.urmichm.placesearchktx.Diana
import com.github.urmichm.placesearchktx.placesearch.FindPlace
import com.github.urmichm.placesearchktx.placesearch.NearbySearch
import com.github.urmichm.placesearchktx.placesearch.TextSearch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("MainActivity" ,FindPlace.TAG)
        Log.i("MainActivity" ,NearbySearch.TAG)
        Log.i("MainActivity" , TextSearch.TAG)

        setContentView(R.layout.activity_main)
    }
}