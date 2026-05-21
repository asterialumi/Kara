package lumi.projects.kara.screens.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import lumi.projects.kara.R
import lumi.projects.kara.screens.login.LoginActivity

class HomeFragment : Fragment(R.layout.fragment_home), HomeContract.View {

    private lateinit var presenter: HomePresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomePresenter(this)

        // UI Setup
        val btnLogout = view.findViewById<Button>(R.id.buttonLogout)
        btnLogout.setOnClickListener { presenter.onLogoutClicked() }

        // Logic to refresh data
        presenter.start()
    }

    override fun displayProjects(projects: List<String>) {
        // TODO: Update your RecyclerView or simple TextView list here
    }

    override fun displayTags(tags: List<String>) {
        // TODO: Update your Tag list here
    }

    override fun navigateToLogin() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}