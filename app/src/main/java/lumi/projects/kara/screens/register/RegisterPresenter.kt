package lumi.projects.kara.screens.register

import lumi.projects.kara.data.model.UserInfo
import lumi.projects.kara.data.repository.DataRepository

class RegisterPresenter(private val view: RegisterContract.View) : RegisterContract.Presenter {

    override fun onRegisterButtonClicked(username: String, password: String, confirm: String) {
        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            view.showEmptyFieldsMessage()
            return
        }

        if (password != confirm) {
            view.showPasswordMismatchMessage()
            return
        }

        val success = DataRepository.register(UserInfo(username, password))

        if (success) {
            view.showSuccessMessage()
            view.navigateToLoginScreen()
        } else {
            view.showUsernameExistsMessage()
        }
    }
}