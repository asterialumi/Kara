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
        presenter = HomePresenter(this, HomeModel())

        getButtonView(R.id.buttonLogout).setOnClickListener { presenter.onLogoutClicked() }

        view.findViewById<ImageButton>(R.id.btn_add_project).setOnClickListener {
            showInputDialog("New Project", "Enter project name") { name ->
                presenter.onAddProjectClicked(name)
                snack("Project '$name' added!")
            }
        }

        view.findViewById<ImageButton>(R.id.btn_add_tag).setOnClickListener {
            showInputDialog("New Tag", "Enter tag name") { tagName ->
                presenter.onAddTagClicked(tagName)
                snack("Tag '#$tagName' added!")
            }
        }

        view.findViewById<TextClock>(R.id.dashboard_clock).timeZone = "Asia/Manila"

        // Logic to refresh data
        presenter.start()
    }

    override fun displayProjects(projects: List<String>) {
        val container = view?.findViewById<LinearLayout>(R.id.project_list_container)
        container?.removeAllViews()

        if (projects.isEmpty()) {
            val emptyTv = TextView(requireContext()).apply {
                text = "No projects created yet. Click + to add one!"
                alpha = 0.5f
                setPadding(0, 16, 0, 16)
            }
            container?.addView(emptyTv)
            return
        }

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

        if (tags.isEmpty()) {
            val emptyTv = TextView(requireContext()).apply {
                text = "No tags created yet. Click + to add one!"
                alpha = 0.5f
                setPadding(0, 16, 0, 16)
            }
            container?.addView(emptyTv)
            return
        }

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
}