package lumi.projects.kara.screens.register

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity // Use this instead of Activity
import lumi.projects.kara.R
import lumi.projects.kara.screens.login.LoginActivity

class RegisterActivity : AppCompatActivity(), RegisterContract.View {
    //we used AppCompatActivity so we can use finish()
    //finish() ensures the user cannot press back to go back to this screen
    //essentially removing this from history when its done

    private lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter = RegisterPresenter(this)

        val btnRegister = findViewById<Button>(R.id.buttonRegister)
        val tvReturn = findViewById<TextView>(R.id.textviewReturnLogin)

        btnRegister.setOnClickListener {
            val username = findViewById<EditText>(R.id.edittextNewUsername).text.toString()
            val password = findViewById<EditText>(R.id.edittextNewPassword).text.toString()
            val confirm = findViewById<EditText>(R.id.edittextConfirmPassword).text.toString()
            presenter.onRegisterButtonClicked(username, password, confirm)
        }

        tvReturn.setOnClickListener {
            navigateToLoginScreen()
        }
    }

    override fun showError(errorText: String) {
        Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Closes Register so it's removed from the history
    }
}