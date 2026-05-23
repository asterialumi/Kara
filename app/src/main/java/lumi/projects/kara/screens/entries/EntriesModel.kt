package lumi.projects.kara.screens.entries

import lumi.projects.kara.data.model.TimeEntry
import lumi.projects.kara.data.repository.DataRepository

class EntriesModel : EntriesContract.Model {

    override fun getAllEntriesReversed(): List<TimeEntry> {
        // We move the sorting logic here
        return DataRepository.timeEntries.reversed()
    }

    override fun removeEntry(id: String) {
        DataRepository.timeEntries.removeAll { it.id == id }
        // Save the updated list back to disk
        DataRepository.saveAllEntries()
    }

    override fun isEmpty(): Boolean = DataRepository.timeEntries.isEmpty()
}