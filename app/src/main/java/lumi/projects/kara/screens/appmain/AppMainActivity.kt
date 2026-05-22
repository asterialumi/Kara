package lumi.projects.kara.screens.appmain

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
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

class AppMainActivity : AppCompatActivity() {
    // we used AppCompatActivity so we can use finish()
    // finish() ensures the user cannot "back button" to go back to this screen
    // essentially removing this from history when its done
    // you'll see other activities/fragments here using AppCompat for the same reason

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!DataRepository.isLoggedIn()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            //removes this from history
            finish()
            return
        }



        setContentView(R.layout.activity_main)

        // Set-up Views
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val headerTitle = findViewById<TextView>(R.id.header_title)
        val btnSettings = findViewById<ImageButton>(R.id.header_settings)

        // Load the HomeFragment by default
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        // Handle navigation clicks
        bottomNav.setOnItemSelectedListener { item ->
            val (fragment, title) = when (item.itemId) {
                R.id.nav_home -> HomeFragment() to "Dashboard"
                R.id.nav_timer -> TimerFragment() to "Tracker"
                R.id.nav_entries -> EntriesFragment() to "Entries"
                R.id.nav_stats -> StatsFragment() to "Profile"
                else -> HomeFragment() to "Dashboard"
            }
            headerTitle.text = title
            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        //Fade transition between fragments
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}