package lumi.projects.kara.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import lumi.projects.kara.R
import lumi.projects.kara.data.repository.DataRepository
import lumi.projects.kara.screens.entries.EntriesFragment
import lumi.projects.kara.screens.home.HomeFragment
import lumi.projects.kara.screens.login.LoginActivity
import lumi.projects.kara.screens.stats.StatsFragment
import lumi.projects.kara.screens.timer.TimerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!DataRepository.isLoggedIn()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Important: Closes MainActivity so they can't "back button" into it
            return
        }


        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // 1. Load the HomeFragment by default
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        // 2. Handle Navigation Clicks
        bottomNav.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_timer -> TimerFragment()
                R.id.nav_entries -> EntriesFragment()
                R.id.nav_stats -> StatsFragment()
                else -> HomeFragment()
            }
            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}