package lumi.projects.kara.screens.entries

import lumi.projects.kara.data.repository.DataRepository

class EntriesPresenter(private val view: EntriesContract.View) : EntriesContract.Presenter {
    override fun start() {
        // Show newest first
        val entries = DataRepository.timeEntries.reversed()
        view.showEntries(entries)
        view.showEmptyState(entries.isEmpty())
    }

    override fun deleteEntry(entryId: String) {
        DataRepository.timeEntries.removeAll { it.id == entryId }
        // Save the updated list back to disk
        DataRepository.saveAllEntries()
        start()
    }
}