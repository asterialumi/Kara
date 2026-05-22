package lumi.projects.kara.screens.register

interface RegisterContract {
    interface View {
        fun showSuccessMessage()
        fun showEmptyFieldsMessage()
        fun showPasswordMismatchMessage()
        fun showUsernameExistsMessage()
        fun navigateToLoginScreen()
    }

    interface Presenter {
        fun onRegisterButtonClicked(username: String, password: String, confirm: String)
    }
}