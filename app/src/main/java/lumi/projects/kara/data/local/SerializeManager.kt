package lumi.projects.kara.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import lumi.projects.kara.data.model.TimeEntry
import lumi.projects.kara.data.model.UserInfo

class SerializeManager(context: Context) {
    private val context = context
    private val gson = Gson()

    // Setting up SharedPreferences
    // (a local save-state alternative to a database)
    private val accountPrefs = context.getSharedPreferences("KARA_GLOBAL_ACCOUNTS", Context.MODE_PRIVATE)
    private var dataPrefs: SharedPreferences? = null

    fun initUserSession(username: String) {
        dataPrefs = context.getSharedPreferences("KARA_DATA_$username", Context.MODE_PRIVATE)
    }

    //FOR USERS
    fun saveSession(username: String?) {
        accountPrefs.edit().putString("current_user", username).apply()
    }

    fun getCurrentUser(): String? {
        return accountPrefs.getString("current_user", null)
    }

    fun saveUsers(users: List<UserInfo>) {
        accountPrefs.edit().putString("users_list", gson.toJson(users)).apply()
    }

    fun getUsers(): MutableList<UserInfo> {
        val json = accountPrefs.getString("users_list", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<UserInfo>>() {}.type
        return gson.fromJson(json, type)
    }

    //FOR PROJECTS
    fun saveProjects(projects: List<String>) {
        dataPrefs?.edit()?.putString("projects", gson.toJson(projects))?.apply()
    }

    fun getProjects(): MutableList<String> {
        val json = dataPrefs?.getString("projects", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(json, type)
    }

    //FOR TAGS
    fun saveTags(tags: List<String>) {
        dataPrefs?.edit()?.putString("tags", gson.toJson(tags))?.apply()
    }

    fun getTags(): MutableList<String> {
        val json = dataPrefs?.getString("tags", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(json, type)
    }

    //FOR ENTRIES
    fun saveEntries(entries: List<TimeEntry>) {
        dataPrefs?.edit()?.putString("entries", gson.toJson(entries))?.apply()
    }

    fun getEntries(): MutableList<TimeEntry> {
        val json = dataPrefs?.getString("entries", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<TimeEntry>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveActiveTimer(startTime: Long, projectName: String?, accumulated: Long, description: String?, tags: String?) {
        dataPrefs?.edit()
            ?.putLong("active_timer_start", startTime)
            ?.putString("active_project", projectName)
            ?.putLong("accumulated_millis", accumulated)
            ?.putString("active_description", description)
            ?.putString("active_tags_input", tags)
            ?.apply()
    }

    fun getActiveTimerStart(): Long = dataPrefs?.getLong("active_timer_start", 0L) ?: 0L
    fun getActiveProject(): String? = dataPrefs?.getString("active_project", null)
    fun getAccumulatedMillis(): Long = dataPrefs?.getLong("accumulated_millis", 0L) ?: 0L
    fun getActiveDescription(): String = dataPrefs?.getString("active_description", "") ?: ""
    fun getActiveTagsInput(): String = dataPrefs?.getString("active_tags_input", "") ?: ""
}