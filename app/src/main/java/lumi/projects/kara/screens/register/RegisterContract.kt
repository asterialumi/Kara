package lumi.projects.kara.screens.register

interface RegisterContract {
    interface View {
        fun showError(errorText: String)
        fun navigateToLoginScreen()
    }

    interface Presenter {
        fun onRegisterButtonClicked(username: String, password: String, confirm: String)
    }
}