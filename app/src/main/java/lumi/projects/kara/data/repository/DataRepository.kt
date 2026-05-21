package lumi.projects.kara.data.repository

import android.content.Context
import lumi.projects.kara.data.local.SerializeManager
import lumi.projects.kara.data.model.TimeEntry

object DataRepository {
    private lateinit var prefs: SerializeManager

    var projects = mutableListOf<String>()
    var tags = mutableListOf<String>()
    var timeEntries = mutableListOf<TimeEntry>()

    // Call this once from your MainActivity or Application class
    fun init(context: Context) {
        prefs = SerializeManager(context)
        projects = prefs.getProjects()
        tags = prefs.getTags()
        timeEntries = prefs.getEntries()
    }

    fun addProject(name: String) {
        projects.add(name)
        prefs.saveProjects(projects)
    }

    fun addTag(name: String) {
        tags.add(name)
        prefs.saveTags(tags)
    }

    fun saveEntry(entry: TimeEntry) {
        timeEntries.add(entry)
        prefs.saveEntries(timeEntries)
    }
}