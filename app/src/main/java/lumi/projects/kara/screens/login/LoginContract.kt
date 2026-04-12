package lumi.projects.kara.screens.login;

import lumi.projects.kara.data.models.UserInfo

interface LoginContract {
    interface View {
        fun showError(errorText: String)
        fun navigateToHomeScreen(username: String)
    }

    interface Presenter {
        fun onLoginClicked(username: String, password: String)
    }
}
