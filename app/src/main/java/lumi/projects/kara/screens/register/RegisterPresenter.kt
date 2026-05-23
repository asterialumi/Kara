package lumi.projects.kara.screens.register

class RegisterPresenter(
    private val view: RegisterContract.View,
    private val model: RegisterContract.Model
) : RegisterContract.Presenter {

    override fun onRegisterButtonClicked(username: String, password: String, confirm: String) {
        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            view.showEmptyFieldsMessage()
            return
        }

        if (password != confirm) {
            view.showPasswordMismatchMessage()
            return
        }

        // Presenter asks the Model to perform the registration
        val success = model.registerUser(username, password)

        if (success) {
            view.showSuccessMessage()
            view.navigateToLoginScreen()
        } else {
            view.showUsernameExistsMessage()
        }
    }
}