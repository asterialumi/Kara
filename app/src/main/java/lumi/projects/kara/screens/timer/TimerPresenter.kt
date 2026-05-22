package lumi.projects.kara.screens.timer

import android.os.Handler
import android.os.Looper
import lumi.projects.kara.data.repository.DataRepository
import lumi.projects.kara.data.model.TimeEntry
import lumi.projects.kara.utils.*

class TimerPresenter(private val view: TimerContract.View) : TimerContract.Presenter {

    private val handler = Handler(Looper.getMainLooper())
    private var isRunning = false

    // The Timer Logic
    private val runnable = object : Runnable {
        override fun run() {
            val currentChunk = System.currentTimeMillis() - DataRepository.activeTimerStart
            val totalElapsed = DataRepository.accumulatedMillis + currentChunk

            view.updateStopwatchText(totalElapsed.toStopwatchFormat())
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

        // If the app was closed and timer was paused
        else if (DataRepository.accumulatedMillis > 0L) {
            isRunning = false
            view.showPausedState()
            view.updateStopwatchText(DataRepository.accumulatedMillis.toStopwatchFormat())
        }
    }

    override fun onStartButtonClicked() {
        if (!isRunning) {
            // If it's a resume
            if (DataRepository.accumulatedMillis > 0L) {
                DataRepository.resumeTimer()
            } else {
                // If it's a brand new start
                DataRepository.startTimer(view.getSelectedProject())
            }

            isRunning = true
            view.showRunningState()
            handler.post(runnable)
        } else {
            // If it's a pause
            val currentChunk = System.currentTimeMillis() - DataRepository.activeTimerStart
            val totalSoFar = DataRepository.accumulatedMillis + currentChunk

            DataRepository.pauseTimer(totalSoFar)

            isRunning = false
            handler.removeCallbacks(runnable)
            view.showPausedState()
        }
    }

    override fun onStopButtonClicked() {
        // Calculate final total
        val finalElapsed = if (isRunning) {
            val currentChunk = System.currentTimeMillis() - DataRepository.activeTimerStart
            DataRepository.accumulatedMillis + currentChunk
        } else {
            DataRepository.accumulatedMillis
        }

        // Parse tag list
        val tagsString = view.getTagsInput()
        val tagsList = if (tagsString.isNotEmpty()) {
            tagsString.split(",").map { it.trim() }
        } else emptyList()

        val entry = TimeEntry(
            projectName = view.getSelectedProject(),
            description = view.getDescription(),
            startTime = System.currentTimeMillis() - finalElapsed,
            endTime = System.currentTimeMillis(),
            tags = tagsList
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
}