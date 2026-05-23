package lumi.projects.kara.screens.timer

import lumi.projects.kara.data.model.TimeEntry

interface TimerContract {
    interface View {
        fun updateStopwatchText(formattedTime: String)
        fun showRunningState()
        fun showPausedState()
        fun showStoppedState()
        fun getSelectedProject(): String
        fun getDescription(): String
        fun getTagsInput(): String
        fun showSaveSuccess()
        fun setupProjectSpinner(projects: List<String>)
    }

    interface Presenter {
        fun start()
        fun onPauseCalled(description: String, tags: String)
        fun onStartButtonClicked()
        fun onStopButtonClicked()
        fun onCleared()
    }

    interface Model {
        fun getProjects(): List<String>
        fun getTags(): List<String>
        fun getActiveTimerStart(): Long
        fun getAccumulatedMillis(): Long
        fun getActiveDescription(): String
        fun getActiveTagsInput(): String

        fun startTimer(projectName: String)
        fun pauseTimer(elapsed: Long)
        fun resumeTimer()
        fun stopTimer()
        fun updateMetadata(desc: String, tags: String)
        fun saveEntry(entry: TimeEntry)
        fun addTagIfNew(tag: String)
    }
}