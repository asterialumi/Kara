package lumi.projects.kara.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import lumi.projects.kara.R
import lumi.projects.kara.screens.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // 1. Load the HomeFragment by default
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        // 2. Handle Navigation Clicks
        bottomNav.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_timer -> HomeFragment()
                R.id.nav_entries -> HomeFragment() // Create these as empty fragments for now
                R.id.nav_stats -> HomeFragment()
                R.id.nav_settings -> HomeFragment()
                else -> HomeFragment()
            }
            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}