package lumi.projects.kara.screens.timer

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import lumi.projects.kara.R

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

        // Init UI
        tvStopwatch = view.findViewById(R.id.textview_stopwatch)
        btnStart = view.findViewById(R.id.btn_start_pause)
        btnStop = view.findViewById(R.id.btn_stop)
        btnSpace = view.findViewById(R.id.btn_space)
        spinnerProject = view.findViewById(R.id.spinner_project)
        etDescription = view.findViewById(R.id.edittext_description)

        // Init Presenter
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
        btnStop.visibility = View.VISIBLE
        btnSpace.visibility = View.VISIBLE
    }

    override fun showStoppedState() {
        btnStart.text = "▶"
        btnStop.visibility = View.GONE
        btnSpace.visibility = View.GONE
        etDescription.text.clear()
    }

    override fun getSelectedProject(): String = spinnerProject.selectedItem.toString()

    override fun getDescription(): String = etDescription.text.toString()

    override fun showSaveSuccess() {
        Toast.makeText(context, "Entry Saved!", Toast.LENGTH_SHORT).show()
    }

    override fun setupProjectSpinner(projects: List<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, projects)
        spinnerProject.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onCleared() // Stop the ticking when user leaves the screen
    }
}