package com.example.android.bringyouumbrella

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.bringyouumbrella.weatherMain.WeatherFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.weatherFragment,
                WeatherFragment()
            )
            .commit()
    }
}
