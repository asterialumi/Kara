package lumi.projects.kara.screens.stats

import lumi.projects.kara.data.model.StatItem
import lumi.projects.kara.utils.*

class StatsPresenter(
    private val view: StatsContract.View,
    private val model: StatsContract.Model
) : StatsContract.Presenter {

    override fun start() {
        // Fetch data via the Model bridge
        val entries = model.getTimeEntries()
        val userName = model.getLoggedInUser()

        view.showUserHeader("${userName.uppercase()}'S TIME TRACKED")

        // 1. Calculate Total Time
        val totalMillis = entries.sumOf { it.durationMillis }
        view.showTotalTime(totalMillis.toStatFormat())

        // 2. Group by Projects and Sort (Using StatItem)
        val projectStats = entries.groupBy { it.projectName }
            .map { (name, projectEntries) ->
                StatItem(name, projectEntries.sumOf { it.durationMillis })
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
        val tagStats = tagMap.map { StatItem(it.key, it.value) }
            .sortedByDescending { it.totalDurationMillis }
        view.showTagStats(tagStats)
    }
}