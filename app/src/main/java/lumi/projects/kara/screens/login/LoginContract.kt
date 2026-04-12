package lumi.projects.kara.screens.login;

import lumi.projects.kara.data.models.UserInfo

class LoginContract {
    interface View {
        fun showError(errorText: String)
        fun navigateToHomeScreen(user: UserInfo)
        fun getRegisteredUser(): UserInfo?
    }

    interface Presenter {
        fun onLoginClicked(username: String, password: String)
    }
}
