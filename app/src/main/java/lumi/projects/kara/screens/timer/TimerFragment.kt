package lumi.projects.kara.screens.timer

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import lumi.projects.kara.R
import lumi.projects.kara.utils.*

class TimerFragment : Fragment(R.layout.fragment_timer), TimerContract.View {

    private lateinit var presenter: TimerPresenter

    // UI elements
    private lateinit var tvStopwatch: TextView
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnSpace: View
    private lateinit var spinnerProject: Spinner
    private lateinit var etDescription: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvStopwatch = getTextView(R.id.textview_stopwatch)
        btnStart = getButtonView(R.id.btn_start_pause)
        btnStop = getButtonView(R.id.btn_stop)

        btnSpace = view.findViewById(R.id.btn_space)
        spinnerProject = view.findViewById(R.id.spinner_project)
        etDescription = view.findViewById(R.id.edittext_description)

        presenter = TimerPresenter(this)

        btnStart.setOnClickListener { presenter.onStartButtonClicked() }
        btnStop.setOnClickListener { presenter.onStopButtonClicked() }

        presenter.start()
    }

    override fun updateStopwatchText(formattedTime: String) {
        tvStopwatch.text = formattedTime
    }

    override fun showRunningState() {
        btnStart.text = "||"
        btnStop.visible()
        btnSpace.visible()
    }

    override fun showStoppedState() {
        btnStart.text = "▶"
        btnStop.gone()
        btnSpace.gone()
        etDescription.text.clear()
        hideKeyboard()
    }

    override fun getSelectedProject(): String = spinnerProject.selectedItem.toString()

    override fun getDescription(): String = getEditTextValue(R.id.edittext_description)

    override fun showSaveSuccess() {
        snack("Entry Saved!")
    }

    override fun setupProjectSpinner(projects: List<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, projects)
        spinnerProject.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Stop the ticking when user leaves the screen
        presenter.onCleared()
    }
}