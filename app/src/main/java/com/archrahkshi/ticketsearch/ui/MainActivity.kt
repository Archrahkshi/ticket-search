package com.archrahkshi.ticketsearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.archrahkshi.ticketsearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var views: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityMainBinding.inflate(layoutInflater)
        setContentView(views.root)
    }
}
