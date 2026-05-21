package lumi.projects.kara.screens.timer

interface TimerContract {
    interface View {
        fun updateStopwatchText(formattedTime: String)
        fun showRunningState()
        fun showStoppedState()
        fun getSelectedProject(): String
        fun getDescription(): String
        fun showSaveSuccess()
        fun setupProjectSpinner(projects: List<String>)
    }

    interface Presenter {
        fun start() // Called when Fragment opens
        fun onStartButtonClicked()
        fun onStopButtonClicked()
        fun onCleared() // To stop handler when fragment is destroyed
    }
}