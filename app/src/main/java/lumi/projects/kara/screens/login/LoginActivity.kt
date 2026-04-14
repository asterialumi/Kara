package lumi.projects.kara.screens.login

import LoginPresenter
import android.app.Activity
import android.os.Bundle
import lumi.projects.kara.R
import lumi.projects.kara.screens.home.HomeActivity
import lumi.projects.kara.utils.*

class LoginActivity : Activity(), LoginContract.View {
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this, LoginModel())

        getButtonView(R.id.buttonLogin).setOnClickListener {
            val username = getEditTextValue(R.id.edittextUsername)
            val password = getEditTextValue(R.id.edittextPassword)
            presenter.login(username, password)
        }
    }

    override fun showSuccessMessage() {
        toast("Login successful!")
    }

    override fun showInvalidCredentialMessage() {
        toast("Invalid credentials!")
    }

    override fun showEmptyMessage() {
        toast("Fields cannot be empty!")
    }

    override fun navigateToHomeScreen() {
        start(HomeActivity::class.java)
    }

    override fun showGenericErrorMessage() {
        toast("Unexpected error occurred")
    }
}