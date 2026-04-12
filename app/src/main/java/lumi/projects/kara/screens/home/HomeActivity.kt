package lumi.projects.kara.screens.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import lumi.projects.kara.R

class HomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val textviewWelcome = findViewById<TextView>(R.id.textviewWelcome)
//        intent?.getStringExtra("username")?.let {
//            textviewWelcome.text = "Welcome $it!"
//        }

        textviewWelcome.text = "Welcome " + intent.getStringExtra("username") + "!";
        //Logout

//        textviewWelcome.setOnClickListener {
//            val intent = Intent(this, ProfileActivity::class.java)
//            intent.putExtra("username", username)
//            intent.putExtra("email", email)
//            startActivity(intent)
//        }
    }
}