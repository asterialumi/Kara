package lumi.projects.kara.screens.entries

class EntriesPresenter(
    private val view: EntriesContract.View,
    private val model: EntriesContract.Model
) : EntriesContract.Presenter {

    override fun start() {
        // Presenter asks Model for the data
        val entries = model.getAllEntriesReversed()

        view.showEntries(entries)
        view.showEmptyState(model.isEmpty())
    }

    override fun deleteEntry(entryId: String) {
        // Presenter tells Model to perform deletion
        model.removeEntry(entryId)

        // Refresh UI
        start()
    }
}