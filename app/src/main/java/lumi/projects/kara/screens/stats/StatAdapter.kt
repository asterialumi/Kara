package lumi.projects.kara.screens.stats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lumi.projects.kara.R

class StatAdapter(private val stats: List<StatModel>) : RecyclerView.Adapter<StatAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_stat_name)
        val tvDuration: TextView = view.findViewById(R.id.tv_stat_duration)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stat_row, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = stats[position]
        holder.tvName.text = item.name
        val hours = item.totalDurationMillis / (1000 * 60 * 60)
        val minutes = (item.totalDurationMillis / (1000 * 60)) % 60
        holder.tvDuration.text = "${hours}h ${minutes}m"
    }
    override fun getItemCount() = stats.size
}