package com.archrahkshi.ticketsearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var views: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityMainBinding.inflate(layoutInflater)
        setContentView(views.root)

        with(views.menuBar) {
            listOf(hotelsTab, pinTab, subscriptionsTab, profileTab).forEach {
                it.setOnClickListener {
                    openNyiFragment()
                }
            }
        }
    }

    private fun openNyiFragment() {
        val fragment = supportFragmentManager.fragments.find { it is NyiFragment }
        if (fragment == null || !fragment.isVisible) {
            supportFragmentManager.commit {
                add(R.id.fragment_container, NyiFragment())
                addToBackStack(null)
            }
        }
    }
}
