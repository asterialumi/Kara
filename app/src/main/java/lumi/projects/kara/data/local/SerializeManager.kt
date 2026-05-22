package lumi.projects.kara.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import lumi.projects.kara.data.model.TimeEntry
import lumi.projects.kara.data.model.UserInfo

class SerializeManager(context: Context) {
    // Setting up SharedPreferences
    // (a local save-state alternative to a database)
    private val prefs: SharedPreferences = context.getSharedPreferences("kara_prefs_fin", Context.MODE_PRIVATE)
    private val gson = Gson()


    //FOR PROJECTS
    fun saveProjects(projects: List<String>) {
        prefs.edit().putString("projects", gson.toJson(projects)).apply()
    }

    fun getProjects(): MutableList<String> {
        val json = prefs.getString("projects", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(json, type)
    }

    //FOR TAGS
    fun saveTags(tags: List<String>) {
        prefs.edit().putString("tags", gson.toJson(tags)).apply()
    }

    fun getTags(): MutableList<String> {
        val json = prefs.getString("tags", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(json, type)
    }

    //FOR ENTRIES
    fun saveEntries(entries: List<TimeEntry>) {
        prefs.edit().putString("entries", gson.toJson(entries)).apply()
    }

    fun getEntries(): MutableList<TimeEntry> {
        val json = prefs.getString("entries", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<TimeEntry>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveActiveTimer(startTime: Long, projectName: String?, accumulated: Long) {
        prefs.edit()
            .putLong("active_timer_start", startTime)
            .putString("active_project", projectName)
            .putLong("accumulated_millis", accumulated)
            .apply()
    }

    fun getActiveTimerStart(): Long = prefs.getLong("active_timer_start", 0L)
    fun getActiveProject(): String? = prefs.getString("active_project", null)
    fun getAccumulatedMillis(): Long = prefs.getLong("accumulated_millis", 0L)

    //FOR USERS
    fun saveSession(username: String?) {
        prefs.edit().putString("current_user", username).apply()
    }

    fun getCurrentUser(): String? {
        return prefs.getString("current_user", null)
    }

    fun saveUsers(users: List<UserInfo>) {
        prefs.edit().putString("users", gson.toJson(users)).apply()
    }

    fun getUsers(): MutableList<UserInfo> {
        val json = prefs.getString("users", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<UserInfo>>() {}.type
        return gson.fromJson(json, type)
    }
}