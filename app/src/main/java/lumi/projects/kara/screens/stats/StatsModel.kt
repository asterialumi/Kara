package lumi.projects.kara.screens.stats

import lumi.projects.kara.data.repository.DataRepository
import lumi.projects.kara.data.model.TimeEntry

class StatsModel : StatsContract.Model {
    override fun getLoggedInUser(): String = DataRepository.getLoggedInUser()
    override fun getTimeEntries(): List<TimeEntry> = DataRepository.timeEntries
}