package lumi.projects.kara.screens.entries

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import lumi.projects.kara.R
import lumi.projects.kara.data.model.TimeEntry
import lumi.projects.kara.utils.*

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
        val chipGroup: com.google.android.material.chip.ChipGroup = view.findViewById(R.id.cg_tags)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_time_entry))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.tvProject.text = entry.projectName
        holder.tvDesc.text = entry.description
        holder.tvDuration.text = entry.durationMillis.toStopwatchFormat()
        holder.tvDate.text = entry.startTime.toDateString()
        holder.chipGroup.removeAllViews()

        entry.tags.forEach { tag ->
            val chip = com.google.android.material.chip.Chip(holder.itemView.context).apply {
                text = tag
                textSize = 10f
                // Make them small and themed
                setChipBackgroundColorResource(R.color.kara_background)
                setTextColor(ContextCompat.getColor(context, R.color.kara_primary))
                alpha = 0.8f
                chipStrokeWidth = 0f
                isClickable = false
            }
            holder.chipGroup.addView(chip)
        }

        holder.btnDelete.setOnClickListener { onDelete(entry.id) }
    }

    override fun getItemCount() = entries.size

    fun updateData(newEntries: List<TimeEntry>) {
        this.entries = newEntries
        notifyDataSetChanged()
    }
}