package lumi.projects.kara.screens.stats

import lumi.projects.kara.data.repository.DataRepository
import lumi.projects.kara.utils.*

class StatsPresenter(private val view: StatsContract.View) : StatsContract.Presenter {

    override fun start() {
        val entries = DataRepository.timeEntries

        val userName = DataRepository.getLoggedInUser()
        view.showUserHeader("${userName.uppercase()}'S TIME TRACKED")

        // 1. Calculate Total Time
        val totalMillis = entries.sumOf { it.durationMillis }
        view.showTotalTime(totalMillis.toStatFormat())

        // 2. Group by Projects and Sort
        val projectStats = entries.groupBy { it.projectName }
            .map { (name, projectEntries) ->
                StatModel(name, projectEntries.sumOf { it.durationMillis })
            }
            .sortedByDescending { it.totalDurationMillis }
        view.showProjectStats(projectStats)

        // 3. Group by Tags and Sort
        val tagMap = mutableMapOf<String, Long>()
        entries.forEach { entry ->
            entry.tags?.filter { it.isNotBlank() }?.forEach { tag ->
                val cleanTag = tag.trim().lowercase()
                tagMap[cleanTag] = (tagMap[cleanTag] ?: 0L) + entry.durationMillis
            }
        }
        val tagStats = tagMap.map { StatModel(it.key, it.value) }
            .sortedByDescending { it.totalDurationMillis }
        view.showTagStats(tagStats)
    }

}