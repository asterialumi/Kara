package lumi.projects.kara.screens.timer

import lumi.projects.kara.data.model.TimeEntry
import lumi.projects.kara.data.repository.DataRepository

class TimerModel : TimerContract.Model {
    override fun getProjects() = DataRepository.projects
    override fun getTags() = DataRepository.tags
    override fun getActiveTimerStart() = DataRepository.activeTimerStart
    override fun getAccumulatedMillis() = DataRepository.accumulatedMillis
    override fun getActiveDescription() = DataRepository.activeDescription
    override fun getActiveTagsInput() = DataRepository.activeTagsInput

    override fun startTimer(projectName: String) = DataRepository.startTimer(projectName)
    override fun pauseTimer(elapsed: Long) = DataRepository.pauseTimer(elapsed)
    override fun resumeTimer() = DataRepository.resumeTimer()
    override fun stopTimer() = DataRepository.stopTimer()

    override fun updateMetadata(desc: String, tags: String) = DataRepository.updateTimerMetadata(desc, tags)

    override fun saveEntry(entry: TimeEntry) = DataRepository.saveEntry(entry)

    override fun addTagIfNew(tag: String) {
        if (!DataRepository.tags.contains(tag)) {
            DataRepository.addTag(tag)
        }
    }
}