package lumi.projects.kara.screens.home

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextClock
import android.widget.TextView
import androidx.fragment.app.Fragment
import lumi.projects.kara.R
import lumi.projects.kara.screens.login.LoginActivity
import lumi.projects.kara.utils.*

class HomeFragment : Fragment(R.layout.fragment_home), HomeContract.View {

    private lateinit var presenter: HomePresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomePresenter(this)

        getButtonView(R.id.buttonLogout).setOnClickListener { presenter.onLogoutClicked() }

        view.findViewById<ImageButton>(R.id.btn_add_project).setOnClickListener {
            showInputDialog("New Project", "Project name") { name ->
                presenter.onAddProjectClicked(name)
            }
        }

        view.findViewById<TextClock>(R.id.dashboard_clock).timeZone = "Asia/Manila"

        // Logic to refresh data
        presenter.start()
    }

    override fun displayProjects(projects: List<String>) {
        val container = view?.findViewById<LinearLayout>(R.id.project_list_container)
        container?.removeAllViews()

        projects.forEach { name ->
            val textView = TextView(requireContext()).apply {
                text = "• $name"
                textSize = 16f
                setTextColor(color(R.color.text_on_light))
                setPadding(0, 8, 0, 8)
            }
            container?.addView(textView)
        }
    }

    override fun displayTags(tags: List<String>) {
        val container = view?.findViewById<LinearLayout>(R.id.tag_list_container)
        container?.removeAllViews()

        tags.forEach { name ->
            val textView = TextView(requireContext()).apply {
                text = "#$name"
                textSize = 16f
                setTextColor(color(R.color.kara_accent))
                setPadding(0, 8, 0, 8)
            }
            container?.addView(textView)
        }
    }

    override fun navigateToLogin() {
        startClear(LoginActivity::class.java)
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