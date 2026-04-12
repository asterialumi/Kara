package lumi.projects.kara.screens.register

import lumi.projects.kara.data.models.UserInfo
import lumi.projects.kara.data.repositories.UserRepository

class RegisterPresenter(private val view: RegisterContract.View): RegisterContract.Presenter {
    override fun onRegisterButtonClicked(username: String, password: String, confirm: String) {
        if(username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            view.showError("Fields cannot be empty!")
            return
        }

        if(password != confirm) {
            view.showError("Passwords don't match!")
            return
        }

        val success = UserRepository.registerUser(username, password)
        if(success) view.navigateToLoginScreen()
        else view.showError("Username already exists!")
    }
}