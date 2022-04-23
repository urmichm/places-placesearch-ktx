package com.github.urmichm.dianaexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.urmichm.diana.Diana

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Diana.hello()
        setContentView(R.layout.activity_main)
        Diana.bye()
    }
}