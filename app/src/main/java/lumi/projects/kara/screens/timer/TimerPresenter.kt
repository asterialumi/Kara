package lumi.projects.kara.screens.timer

import android.os.Handler
import android.os.Looper
import lumi.projects.kara.data.model.TimeEntry
import lumi.projects.kara.utils.*

class TimerPresenter(
    private val view: TimerContract.View,
    private val model: TimerContract.Model
) : TimerContract.Presenter {

    private val handler = Handler(Looper.getMainLooper())
    private var isRunning = false

    // The Timer Logic
    private val runnable = object : Runnable {
        override fun run() {
            // MATH: Using model state
            val currentChunk = System.currentTimeMillis() - model.getActiveTimerStart()
            val totalElapsed = model.getAccumulatedMillis() + currentChunk

            view.updateStopwatchText(totalElapsed.toStopwatchFormat())
            handler.postDelayed(this, 1000)
        }
    }

    override fun start() {
        view.setupProjectSpinner(model.getProjects())

        // If the app was closed while timer was running, resume it
        if (model.getActiveTimerStart() != 0L) {
            isRunning = true
            view.showRunningState()
            handler.post(runnable)
        }
        // If the app was closed and timer was paused
        else if (model.getAccumulatedMillis() > 0L) {
            isRunning = false
            view.showPausedState()
            view.updateStopwatchText(model.getAccumulatedMillis().toStopwatchFormat())
        }
    }

    override fun onPauseCalled(description: String, tags: String) {
        model.updateMetadata(description, tags)
    }

    override fun onStartButtonClicked() {
        if (!isRunning) {
            // If it's a resume
            if (model.getAccumulatedMillis() > 0L) {
                model.resumeTimer()
            } else {
                // If it's a brand new start
                model.startTimer(view.getSelectedProject())
            }

            isRunning = true
            view.showRunningState()
            handler.post(runnable)
        } else {
            // If it's a pause
            val currentChunk = System.currentTimeMillis() - model.getActiveTimerStart()
            val totalSoFar = model.getAccumulatedMillis() + currentChunk

            model.pauseTimer(totalSoFar)

            isRunning = false
            handler.removeCallbacks(runnable)
            view.showPausedState()
        }
    }

    override fun onStopButtonClicked() {
        // Calculate final total
        val finalElapsed = if (isRunning) {
            val currentChunk = System.currentTimeMillis() - model.getActiveTimerStart()
            model.getAccumulatedMillis() + currentChunk
        } else {
            model.getAccumulatedMillis()
        }

        // Parse tag list
        val tagsString = view.getTagsInput()
        val tagsList = if (tagsString.isNotEmpty()) {
            tagsString.split(",").map { it.trim() }
                .filter { it.isNotBlank() }
        } else emptyList()

        // Sync tags with master list via model
        tagsList.forEach { tag -> model.addTagIfNew(tag) }

        val entry = TimeEntry(
            projectName = view.getSelectedProject(),
            description = view.getDescription(),
            startTime = System.currentTimeMillis() - finalElapsed,
            endTime = System.currentTimeMillis(),
            tags = tagsList
        )

        model.saveEntry(entry)
        model.stopTimer()
        // Clear temp storage
        model.updateMetadata("", "")

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