package lumi.projects.kara.screens.register

import lumi.projects.kara.data.models.UserInfo

interface RegisterContract {
    interface View {
        fun showError(errorText: String)
        fun navigateToLoginScreen()
    }

    interface Presenter {
        fun onRegisterButtonClicked(username: String, password: String, confirm: String)
    }
}