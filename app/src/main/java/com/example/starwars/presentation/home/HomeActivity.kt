package com.example.starwars.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.example.starwars.R

class HomeActivity : AppCompatActivity() {
    private lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()

        supportFragmentManager.beginTransaction().add(
            R.id.root,
            HomeFragment()
        ).commit()
    }

    private fun setupViews() {
        frameLayout = findViewById(R.id.root)
    }
}
