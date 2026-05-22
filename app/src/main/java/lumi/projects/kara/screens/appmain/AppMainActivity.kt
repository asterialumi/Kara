package lumi.projects.kara.screens.appmain

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
import lumi.projects.kara.utils.*

class AppMainActivity : AppCompatActivity() {
    // we used AppCompatActivity so we can use finish()
    // finish() ensures the user cannot "back button" to go back to this screen
    // essentially removing this from history when its done
    // you'll see other activities/fragments here using AppCompat for the same reason

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check Session if there's current user
        if (!DataRepository.isLoggedIn()) {
            startClear(LoginActivity::class.java)
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
                R.id.nav_home -> HomeFragment() to "Home"
                R.id.nav_timer -> TimerFragment() to "Timer"
                R.id.nav_entries -> EntriesFragment() to "Entries"
                R.id.nav_stats -> StatsFragment() to "Stats"
                else -> HomeFragment() to "Home"
            }
            headerTitle.text = title
            loadFragment(fragment)
            true
        }

        btnSettings.setOnClickListener {
            showSettingsDialog()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        //Fade transition between fragments
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun showSettingsDialog() {
        val options = arrayOf("Export Data", "Import Data", "Logout")
        android.app.AlertDialog.Builder(this)
            .setTitle("Settings")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> snack("Data exported to clipboard!") // Placeholder for logic
                    1 -> snack("Import functionality coming soon!")
                    2 -> {
                        DataRepository.logout()
                        startClear(LoginActivity::class.java)
                    }
                }
            }.show()
    }
}