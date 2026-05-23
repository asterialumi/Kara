package lumi.projects.kara.screens.entries

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lumi.projects.kara.R
import lumi.projects.kara.data.model.TimeEntry
import lumi.projects.kara.utils.*

class EntriesFragment : Fragment(R.layout.fragment_entries), EntriesContract.View {
    private lateinit var presenter: EntriesPresenter
    private lateinit var adapter: TimeEntryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = EntriesPresenter(this, EntriesModel())

        adapter = TimeEntryAdapter(emptyList()) { id -> presenter.deleteEntry(id) }
        view.findViewById<RecyclerView>(R.id.rv_entries).setup(adapter)

        presenter.start()
    }

    override fun showEntries(entries: List<TimeEntry>) {
        adapter.updateData(entries)
    }

    override fun showEmptyState(isVisible: Boolean) {
        getTextView(R.id.tv_empty_state).showIf(isVisible)
    }
}