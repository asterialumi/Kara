package lumi.projects.kara.screens.register

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
import lumi.projects.kara.screens.login.LoginActivity

class RegisterActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val edittextNewUsername = findViewById<EditText>(R.id.edittextNewUsername)
        val edittextNewPassword = findViewById<EditText>(R.id.edittextNewPassword)
        val edittextConfirmPassword = findViewById<EditText>(R.id.edittextConfirmPassword)
        val textviewReturnLogin = findViewById<TextView>(R.id.textviewReturnLogin)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        textviewReturnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        buttonSubmit.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            val username = edittextNewUsername.text.toString()
            val password = edittextNewPassword.text.toString()
            val confirm = edittextConfirmPassword.text.toString()

            //TODO
            if(password == confirm && !username.isNullOrEmpty() && !password.isNullOrEmpty()) {
                val registeredUsername = edittextNewUsername.text.toString()
                val registeredPassword = edittextNewPassword.text.toString()

                intent.putExtra("registeredUsername", registeredUsername)
                intent.putExtra("registeredPassword", registeredPassword)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Fields are empty, Passwords don't match!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}