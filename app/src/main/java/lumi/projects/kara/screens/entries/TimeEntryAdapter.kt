package lumi.projects.kara.screens.entries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lumi.projects.kara.R
import lumi.projects.kara.data.model.TimeEntry

class TimeEntryAdapter(
    private var entries: List<TimeEntry>,
    private val onDelete: (String) -> Unit
) : RecyclerView.Adapter<TimeEntryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProject: TextView = view.findViewById(R.id.tv_project_name)
        val tvDesc: TextView = view.findViewById(R.id.tv_description)
        val tvDuration: TextView = view.findViewById(R.id.tv_duration)
        val tvDate: TextView = view.findViewById(R.id.tv_date)
        val btnDelete: ImageButton = view.findViewById(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_time_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.tvProject.text = entry.projectName
        holder.tvDesc.text = entry.description
        holder.tvDuration.text = formatTime(entry.durationMillis)
        holder.tvDate.text = formatDate(entry.startTime)
        holder.btnDelete.setOnClickListener { onDelete(entry.id) }
    }

    override fun getItemCount() = entries.size

    fun updateData(newEntries: List<TimeEntry>) {
        this.entries = newEntries
        notifyDataSetChanged()
    }

    private fun formatTime(millis: Long): String {
        val seconds = (millis / 1000) % 60
        val minutes = (millis / (1000 * 60)) % 60
        val hours = (millis / (1000 * 60 * 60))
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = java.text.SimpleDateFormat("EEEE, MMM dd", java.util.Locale.getDefault())
        return sdf.format(java.util.Date(timestamp))
    }
}