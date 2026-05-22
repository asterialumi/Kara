package lumi.projects.kara.screens.register

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import lumi.projects.kara.R
import lumi.projects.kara.screens.login.LoginActivity
import lumi.projects.kara.utils.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Set-up Views and Presenter
        presenter = RegisterPresenter(this)

        getButtonView(R.id.buttonRegister).setOnClickListener {
            val username = findViewById<EditText>(R.id.edittextNewUsername).text.toString()
            val password = findViewById<EditText>(R.id.edittextNewPassword).text.toString()
            val confirm = findViewById<EditText>(R.id.edittextConfirmPassword).text.toString()
            presenter.onRegisterButtonClicked(username, password, confirm)
        }

        getTextView(R.id.textviewReturnLogin).setOnClickListener {
            navigateToLoginScreen()
        }
    }

    override fun showSuccessMessage() {
        snack("Register successful!")
    }
    override fun showEmptyFieldsMessage() {
        snack("Fields are empty!")
    }
    override fun showPasswordMismatchMessage() {
        snack("Passwords don't match!")
    }
    override fun showUsernameExistsMessage() {
        snack("Username already exists!")
    }

    override fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}