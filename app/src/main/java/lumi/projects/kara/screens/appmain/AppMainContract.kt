package lumi.projects.kara.screens.appmain

import androidx.fragment.app.Fragment

interface AppMainContract {
    interface View {
        fun navigateToLogin()
        fun showHeaderTitle(title: String)
        fun replaceFragment(fragment: Fragment)
        fun showExportSuccess()
        fun showImportSuccess()
        fun showImportError()
        fun showClipboardEmpty()
    }

    interface Presenter {
        fun init()
        fun onNavigationItemSelected(itemId: Int)
        fun onExportClicked()
        fun onImportClicked(clipboardText: String?)
    }

    // THE ACADEMIC MODEL
    interface Model {
        fun isLoggedIn(): Boolean
        fun getExportJson(): String
        fun importJson(json: String): Boolean
    }
}