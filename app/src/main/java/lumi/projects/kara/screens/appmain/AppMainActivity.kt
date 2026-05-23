package lumi.projects.kara.screens.appmain

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import lumi.projects.kara.R
import lumi.projects.kara.data.repository.DataRepository
import lumi.projects.kara.screens.login.LoginActivity
import lumi.projects.kara.utils.*

class AppMainActivity : AppCompatActivity(), AppMainContract.View {

    private lateinit var presenter: AppMainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize MVP components
        presenter = AppMainPresenter(this, AppMainModel())
        presenter.init()

        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val btnSettings = findViewById<ImageButton>(R.id.header_settings)

        if (savedInstanceState == null) {
            presenter.onNavigationItemSelected(R.id.nav_home)
        }

        bottomNav.setOnItemSelectedListener { item ->
            presenter.onNavigationItemSelected(item.itemId)
            true
        }

        btnSettings.setOnClickListener { showSettingsDialog() }
    }

    private fun showSettingsDialog() {
        val options = arrayOf("Export Data", "Import Data")
        android.app.AlertDialog.Builder(this)
            .setTitle("Settings")
            .setItems(options) { _, which ->
                if (which == 0) presenter.onExportClicked()
                else {
                    val cb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val text = cb.primaryClip?.getItemAt(0)?.text?.toString()
                    presenter.onImportClicked(text)
                }
            }.show()
    }

    override fun navigateToLogin() = startClear(LoginActivity::class.java)

    override fun showHeaderTitle(title: String) {
        findViewById<TextView>(R.id.header_title).text = title
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun showExportSuccess() {
        val json = DataRepository.exportToClipboardJson()
        val cb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        cb.setPrimaryClip(ClipData.newPlainText("KaraData", json))
        snack("Data copied to clipboard!")
    }

    override fun showImportSuccess() = snack("Import Success!")
    override fun showImportError() = snack("Invalid data in clipboard!")
    override fun showClipboardEmpty() = snack("Clipboard is empty!")
}