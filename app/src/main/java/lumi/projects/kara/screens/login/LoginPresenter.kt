package lumi.projects.kara.screens.login

import lumi.projects.kara.data.repository.DataRepository

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    override fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            view.showEmptyMessage()
            return
        }

        if (DataRepository.login(username, password)) {
            view.showSuccessMessage()
            view.navigateToHomeScreen()
        } else {
            view.showInvalidCredentialMessage()
        }
    }
}