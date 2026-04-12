package lumi.projects.kara.screens.login

import lumi.projects.kara.data.repositories.UserRepository

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {
    override fun onLoginClicked(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            view.showError("Fields cannot be empty!")
            return
        }

        val user = UserRepository.validateUser(username, password)
        if (user != null) {
            //load entries from a separate repository ("entry repository") of the associated user
            view.navigateToHomeScreen(username)
        } else {
            view.showError("Invalid credentials!")
        }
    }

}