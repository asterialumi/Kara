package lumi.projects.kara.screens.login

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {
    override fun onLoginClicked(username: String, password: String) {
        val registeredUser = view.getRegisteredUser()

        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            view.showError("Fields cannot be empty!")
            return
        }

        if (registeredUser != null && username == registeredUser.username && password == registeredUser.password) {
            view.navigateToHomeScreen(registeredUser)
        } else {
            view.showError("Invalid credentials!")
        }
    }

}