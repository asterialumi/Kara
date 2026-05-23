package lumi.projects.kara.screens.timer

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import lumi.projects.kara.R
import lumi.projects.kara.data.repository.DataRepository
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
    private lateinit var etTags: MultiAutoCompleteTextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvStopwatch = getTextView(R.id.textview_stopwatch)
        btnStart = getButtonView(R.id.btn_start_pause)
        btnStop = getButtonView(R.id.btn_stop)

        btnSpace = view.findViewById(R.id.btn_space)
        spinnerProject = view.findViewById(R.id.spinner_project)
        etDescription = view.findViewById(R.id.edittext_description)
        etTags = view.findViewById(R.id.edittext_tags)

        // Set-up Tag Autocomplete
        val tagAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, DataRepository.tags)
        etTags.setAdapter(tagAdapter)
        etTags.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        // Restore Persisted Text
        etDescription.setText(DataRepository.activeDescription)
        etTags.setText(DataRepository.activeTagsInput)


        presenter = TimerPresenter(this)

        btnStart.setOnClickListener { presenter.onStartButtonClicked() }
        btnStop.setOnClickListener { presenter.onStopButtonClicked() }

        presenter.start()
    }

    override fun onPause() {
        super.onPause()
        // Persist description and tag text
        DataRepository.updateTimerMetadata(getDescription(), getTagsInput())
    }

    override fun updateStopwatchText(formattedTime: String) {
        tvStopwatch.text = formattedTime
    }

    override fun showRunningState() {
        btnStart.text = "||"
        btnStop.visible()
        btnSpace.visible()
    }

    override fun showPausedState() {
        btnStart.text = "▶"
        btnStop.visible()
        btnSpace.visible()
    }

    override fun showStoppedState() {
        btnStart.text = "▶"
        btnStop.gone()
        btnSpace.gone()
        etDescription.text.clear()
        etTags.text.clear()

        // Reset the metadata state
        DataRepository.updateTimerMetadata("", "")
        hideKeyboard()
    }

    override fun getSelectedProject(): String = spinnerProject.selectedItem.toString()

    override fun getDescription(): String = etDescription.text.toString()

    override fun getTagsInput(): String = etTags.text.toString()

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