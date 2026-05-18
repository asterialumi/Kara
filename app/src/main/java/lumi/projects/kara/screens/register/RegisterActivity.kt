package lumi.projects.kara.screens.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import lumi.projects.kara.R
import lumi.projects.kara.screens.login.LoginActivity

class RegisterActivity : Activity(), RegisterContract.View {
    private lateinit var presenter: RegisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter = RegisterPresenter(this)

        val edittextNewUsername = findViewById<EditText>(R.id.edittextNewUsername)
        val edittextNewPassword = findViewById<EditText>(R.id.edittextNewPassword)
        val edittextConfirmPassword = findViewById<EditText>(R.id.edittextConfirmPassword)
        val textviewReturnLogin = findViewById<TextView>(R.id.textviewReturnLogin)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        textviewReturnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        buttonRegister.setOnClickListener {
            val username = edittextNewUsername.text.toString()
            val password = edittextNewPassword.text.toString()
            val confirm = edittextConfirmPassword.text.toString()
            presenter.onRegisterButtonClicked(username, password, confirm)
        }
    }

    override fun showError(errorText: String) {
        Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}