package lumi.projects.kara.screens.stats

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lumi.projects.kara.R
import lumi.projects.kara.data.model.StatItem
import lumi.projects.kara.utils.*

class StatItemAdapter(private val stats: List<StatItem>) : RecyclerView.Adapter<StatItemAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_stat_name)
        val tvDuration: TextView = view.findViewById(R.id.tv_stat_duration)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_stat_row))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = stats[position]
        holder.tvName.text = item.name
        holder.tvDuration.text = item.totalDurationMillis.toStatFormat()
    }
    override fun getItemCount() = stats.size
}