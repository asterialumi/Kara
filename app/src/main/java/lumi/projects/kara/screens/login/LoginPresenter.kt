package lumi.projects.kara.screens.login

class LoginPresenter(
    private val view: LoginContract.View,
    private val model: LoginContract.Model
) : LoginContract.Presenter {

    override fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            view.showEmptyMessage()
            return
        }

        // Presenter asks Model to handle the verification
        if (model.attemptLogin(username, password)) {
            view.showSuccessMessage()
            view.navigateToHomeScreen()
        } else {
            view.showInvalidCredentialMessage()
        }
    }
}