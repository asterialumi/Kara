package lumi.projects.kara.screens.timer

import android.os.Handler
import android.os.Looper
import lumi.projects.kara.data.repository.DataRepository
import lumi.projects.kara.data.model.TimeEntry

class TimerPresenter(private val view: TimerContract.View) : TimerContract.Presenter {

    private val handler = Handler(Looper.getMainLooper())
    private var isRunning = false

    // The Timer Logic
    private val runnable = object : Runnable {
        override fun run() {
            val elapsed = System.currentTimeMillis() - DataRepository.activeTimerStart
            view.updateStopwatchText(formatTime(elapsed))
            handler.postDelayed(this, 1000)
        }
    }

    override fun start() {
        view.setupProjectSpinner(DataRepository.projects)

        // If the app was closed while timer was running, resume it
        if (DataRepository.activeTimerStart != 0L) {
            isRunning = true
            view.showRunningState()
            handler.post(runnable)
        }
    }

    override fun onStartButtonClicked() {
        if (!isRunning) {
            val projectName = view.getSelectedProject()
            DataRepository.startTimer(projectName)

            isRunning = true
            view.showRunningState()
            handler.post(runnable)
        } else {
            // Optional: Implement Pause logic here if desired
        }
    }

    override fun onStopButtonClicked() {
        val endTime = System.currentTimeMillis()
        val entry = TimeEntry(
            projectName = view.getSelectedProject(),
            description = view.getDescription(),
            startTime = DataRepository.activeTimerStart,
            endTime = endTime
        )

        DataRepository.saveEntry(entry)
        DataRepository.stopTimer()

        isRunning = false
        handler.removeCallbacks(runnable)

        view.showStoppedState()
        view.updateStopwatchText("00:00:00")
        view.showSaveSuccess()
    }

    override fun onCleared() {
        handler.removeCallbacks(runnable)
    }

    private fun formatTime(millis: Long): String {
        val seconds = (millis / 1000) % 60
        val minutes = (millis / (1000 * 60)) % 60
        val hours = (millis / (1000 * 60 * 60))
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}