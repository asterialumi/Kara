package lumi.projects.kara.screens.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import lumi.projects.kara.R
import lumi.projects.kara.screens.login.LoginActivity

class HomeActivity : Activity(), HomeContract.View {
    private lateinit var presenter: HomePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter = HomePresenter(this)

        val textviewWelcome = findViewById<TextView>(R.id.textviewWelcome)
        val buttonLogout = findViewById<Button>(R.id.buttonLogout)

        textviewWelcome.text = "Welcome " + intent.getStringExtra("username") + "!"

        buttonLogout.setOnClickListener {
            presenter.onLogoutButtonClicked()
        }
    }

    override fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}