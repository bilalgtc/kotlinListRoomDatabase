package com.example.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val ratingbar :RatingBar = findViewById(R.id.rating)

        ratingbar.rating = 0f
        ratingbar.stepSize  = 1f

        ratingbar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            Toast.makeText(applicationContext,"$fl",Toast.LENGTH_SHORT).show()
        }
    }
}