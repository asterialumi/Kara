package lumi.projects.kara.screens.appmain

import lumi.projects.kara.R
import lumi.projects.kara.screens.entries.EntriesFragment
import lumi.projects.kara.screens.home.HomeFragment
import lumi.projects.kara.screens.stats.StatsFragment
import lumi.projects.kara.screens.timer.TimerFragment

class AppMainPresenter(
    private val view: AppMainContract.View,
    private val model: AppMainContract.Model
) : AppMainContract.Presenter {

    override fun init() {
        if (!model.isLoggedIn()) {
            view.navigateToLogin()
        }
    }

    override fun onNavigationItemSelected(itemId: Int) {
        val (fragment, title) = when (itemId) {
            R.id.nav_home -> HomeFragment() to "Home"
            R.id.nav_timer -> TimerFragment() to "Timer"
            R.id.nav_entries -> EntriesFragment() to "Entries"
            R.id.nav_stats -> StatsFragment() to "Stats"
            else -> HomeFragment() to "Home"
        }
        view.showHeaderTitle(title)
        view.replaceFragment(fragment)
    }

    override fun onExportClicked() {
        // View handles the actual clipboard action
        view.showExportSuccess()
    }

    override fun onImportClicked(clipboardText: String?) {
        if (clipboardText.isNullOrEmpty()) {
            view.showClipboardEmpty()
            return
        }

        if (model.importJson(clipboardText)) {
            view.showImportSuccess()
        } else {
            view.showImportError()
        }
    }
}