package lumi.projects.kara.screens.entries

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lumi.projects.kara.R
import lumi.projects.kara.data.model.TimeEntry

class EntriesFragment : Fragment(R.layout.fragment_entries), EntriesContract.View {
    private lateinit var presenter: EntriesPresenter
    private lateinit var adapter: TimeEntryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = EntriesPresenter(this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_entries)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = TimeEntryAdapter(emptyList()) { id -> presenter.deleteEntry(id) }
        recyclerView.adapter = adapter

        presenter.start()
    }

    override fun showEntries(entries: List<TimeEntry>) {
        adapter.updateData(entries)
    }

    override fun showEmptyState(isVisible: Boolean) {
        view?.findViewById<TextView>(R.id.tv_empty_state)?.visibility =
            if (isVisible) View.VISIBLE else View.GONE
    }
}