package lumi.projects.kara.screens.home

interface HomeContract {
    interface View {
        fun navigateToLoginScreen()
    }

    interface Presenter {
        fun onLogoutButtonClicked()
        fun startTimer()
        fun endTimer()
    }
}