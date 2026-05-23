package lumi.projects.kara.screens.entries

import lumi.projects.kara.data.model.TimeEntry

interface EntriesContract {
    interface View {
        fun showEntries(entries: List<TimeEntry>)
        fun showEmptyState(isVisible: Boolean)
    }
    interface Presenter {
        fun start()
        fun deleteEntry(entryId: String)
    }

    interface Model {
        fun getAllEntriesReversed(): List<TimeEntry>
        fun removeEntry(id: String)
        fun isEmpty(): Boolean
    }
}