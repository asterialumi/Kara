import android.app.Activity
import lumi.projects.kara.data.models.UserInfo
import lumi.projects.kara.screens.login.LoginContract
import lumi.projects.kara.screens.login.LoginModel
import lumi.projects.kara.utils.app

class LoginPresenter(
    private val view: LoginContract.View,
    private val loginModel: LoginModel
) : LoginContract.Presenter {

    private val app = (view as Activity).app()

    override fun login(username: String, password: String) {
        println(loginModel.login(username, password))
        if (username.isNotEmpty() && password.isNotEmpty()) {
            println(loginModel.login(username, password))
            if (loginModel.login(username, password)) {
                app.setUserInfo(UserInfo(username, password))
                view.showSuccessMessage()
                view.navigateToHomeScreen()
            } else {
                view.showInvalidCredentialMessage()
            }
        } else {
            view.showEmptyMessage()
        }
    }
}