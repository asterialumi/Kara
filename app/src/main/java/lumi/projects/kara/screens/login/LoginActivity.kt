package lumi.projects.kara.screens.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import lumi.projects.kara.R
import lumi.projects.kara.data.models.UserInfo
import lumi.projects.kara.screens.home.HomeActivity
import lumi.projects.kara.screens.register.RegisterActivity

class LoginActivity : Activity(), LoginContract.View {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)

        val edittextUsername = findViewById<EditText>(R.id.edittextUsername)
        val edittextPassword = findViewById<EditText>(R.id.edittextPassword)
        val textviewRegister = findViewById<TextView>(R.id.textviewRegister)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        textviewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {
            val username = edittextUsername.text.toString()
            val password = edittextPassword.text.toString()
            presenter.onLoginClicked(username, password)
        }
    }

    override fun showError(errorText: String) {
        Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
    }

    override fun getRegisteredUser(): UserInfo? {
        val username = intent.getStringExtra("registeredUsername")
        val password = intent.getStringExtra("registeredPassword")
        if(!username.isNullOrEmpty() && !password.isNullOrEmpty()) return UserInfo(username, password)
        return null
    }

    override fun navigateToHomeScreen(user: UserInfo) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("username", user.username)
        intent.putExtra("password", user.password)
        startActivity(intent)
    }
}