package lumi.projects.kara.screens.login;

interface LoginContract {

    interface View {
        fun showSuccessMessage()
        fun showInvalidCredentialMessage()
        fun showEmptyMessage()
        fun navigateToHomeScreen()
        fun navigateToRegisterScreen()
        fun showGenericErrorMessage()
    }

    interface Presenter {
        fun login(username: String, password: String)
    }
}
