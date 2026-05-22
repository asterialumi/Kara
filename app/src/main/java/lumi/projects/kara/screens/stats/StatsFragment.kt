package lumi.projects.kara.screens.stats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import lumi.projects.kara.R
import lumi.projects.kara.utils.*

class StatsFragment : Fragment(R.layout.fragment_stats), StatsContract.View {
    private lateinit var presenter: StatsPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = StatsPresenter(this)
        presenter.start()
    }

    override fun showTotalTime(formattedTime: String) {
        getTextView(R.id.tv_total_time).text = formattedTime
    }

    override fun showUserHeader(name: String) {
        getTextView(R.id.tv_total_tracked).text = name
    }

    override fun showProjectStats(stats: List<StatModel>) {
        view?.findViewById<RecyclerView>(R.id.rv_project_stats)?.setup(StatAdapter(stats))
    }

    override fun showTagStats(stats: List<StatModel>) {
        view?.findViewById<RecyclerView>(R.id.rv_tag_stats)?.setup(StatAdapter(stats))
    }
}