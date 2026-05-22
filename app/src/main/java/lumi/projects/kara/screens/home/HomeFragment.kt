package lumi.projects.kara.screens.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextClock
import android.widget.TextView
import androidx.fragment.app.Fragment
import lumi.projects.kara.R
import lumi.projects.kara.screens.login.LoginActivity

class HomeFragment : Fragment(R.layout.fragment_home), HomeContract.View {

    private lateinit var presenter: HomePresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomePresenter(this)


        val btnLogout = view.findViewById<Button>(R.id.buttonLogout)
        btnLogout.setOnClickListener { presenter.onLogoutClicked() }

        val btnAddProject = view.findViewById<ImageButton>(R.id.btn_add_project)
        btnAddProject.setOnClickListener {
            showAddProjectDialog()
        }

        val clock = view.findViewById<TextClock>(R.id.dashboard_clock)
        clock.timeZone = "Asia/Manila"

        // Logic to refresh data
        presenter.start()
    }

    override fun displayProjects(projects: List<String>) {
        val container = view?.findViewById<LinearLayout>(R.id.project_list_container)
        container?.removeAllViews()

        for (projectName in projects) {
            val textView = TextView(requireContext())
            textView.text = "• $projectName"
            textView.setPadding(0, 8, 0, 8)
            textView.setTextColor(resources.getColor(R.color.text_on_light))
            textView.textSize = 16f

            container?.addView(textView)
        }
    }

    override fun displayTags(tags: List<String>) {
        val container = view?.findViewById<LinearLayout>(R.id.tag_list_container)
        container?.removeAllViews()

        for (tagName in tags) {
            val textView = TextView(requireContext())
            textView.text = "#$tagName"
            textView.setPadding(0, 8, 0, 8)
            textView.setTextColor(resources.getColor(R.color.kara_accent))
            textView.textSize = 16f

            container?.addView(textView)
        }
    }

    override fun navigateToLogin() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun showAddProjectDialog() {
        val builder = android.app.AlertDialog.Builder(requireContext())
        builder.setTitle("New Project")
        val input = android.widget.EditText(requireContext())
        input.hint = "Project name"
        builder.setView(input)

        builder.setPositiveButton("Add") { _, _ ->
            val name = input.text.toString()
            if (name.isNotEmpty()) {
                presenter.onAddProjectClicked(name)
            }
        }
        builder.show()
    }
}