package lumi.projects.kara.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import lumi.projects.kara.data.model.TimeEntry

class SerializeManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("kara_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveProjects(projects: List<String>) {
        prefs.edit().putString("projects", gson.toJson(projects)).apply()
    }

    fun getProjects(): MutableList<String> {
        val json = prefs.getString("projects", null) ?: return mutableListOf("Work", "Study")
        val type = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveTags(tags: List<String>) {
        prefs.edit().putString("tags", gson.toJson(tags)).apply()
    }

    fun getTags(): MutableList<String> {
        val json = prefs.getString("tags", null) ?: return mutableListOf("Urgent", "Relax")
        val type = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveEntries(entries: List<TimeEntry>) {
        prefs.edit().putString("entries", gson.toJson(entries)).apply()
    }

    fun getEntries(): MutableList<TimeEntry> {
        val json = prefs.getString("entries", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<TimeEntry>>() {}.type
        return gson.fromJson(json, type)
    }
}