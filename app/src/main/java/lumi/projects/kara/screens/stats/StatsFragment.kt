package lumi.projects.kara.screens.stats

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lumi.projects.kara.R

class StatsFragment : Fragment(R.layout.fragment_stats), StatsContract.View {
    private lateinit var presenter: StatsPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = StatsPresenter(this)
        presenter.start()
    }

    override fun showTotalTime(formattedTime: String) {
        view?.findViewById<TextView>(R.id.tv_total_time)?.text = formattedTime
    }

    override fun showProjectStats(stats: List<StatModel>) {
        val rv = view?.findViewById<RecyclerView>(R.id.rv_project_stats)
        rv?.layoutManager = LinearLayoutManager(context)
        rv?.adapter = StatAdapter(stats)
    }

    override fun showTagStats(stats: List<StatModel>) {
        val rv = view?.findViewById<RecyclerView>(R.id.rv_tag_stats)
        rv?.layoutManager = LinearLayoutManager(context)
        rv?.adapter = StatAdapter(stats)
    }
}