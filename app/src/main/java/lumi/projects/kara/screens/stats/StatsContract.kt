package lumi.projects.kara.screens.stats

import lumi.projects.kara.data.model.StatItem
import lumi.projects.kara.data.model.TimeEntry

interface StatsContract {
    interface View {
        fun showUserHeader(name: String)
        fun showTotalTime(formattedTime: String)
        fun showProjectStats(stats: List<StatItem>)
        fun showTagStats(stats: List<StatItem>)
    }
    interface Presenter {
        fun start()
    }
    interface Model {
        fun getLoggedInUser(): String
        fun getTimeEntries(): List<TimeEntry>
    }

}