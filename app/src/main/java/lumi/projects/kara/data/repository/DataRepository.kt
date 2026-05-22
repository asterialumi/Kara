package lumi.projects.kara.data.repository

import android.content.Context
import lumi.projects.kara.data.local.SerializeManager
import lumi.projects.kara.data.model.TimeEntry
import lumi.projects.kara.data.model.UserInfo

object DataRepository {
    private lateinit var prefs: SerializeManager

    var projects = mutableListOf<String>()
    var tags = mutableListOf<String>()
    var timeEntries = mutableListOf<TimeEntry>()
    private var registeredUsers = mutableListOf<UserInfo>()

    // Call this once from your MainActivity or Application class
    fun init(context: Context) {
        prefs = SerializeManager(context)
        projects = prefs.getProjects()
        tags = prefs.getTags()
        timeEntries = prefs.getEntries()
        registeredUsers = prefs.getUsers()
    }

    fun login(username: String, pword: String): Boolean {
        val user = registeredUsers.find { it.username == username && it.password == pword }
        if (user != null) {
            prefs.saveSession(username) // Mark as logged in
            return true
        }
        return false
    }

    fun logout() {
        prefs.saveSession(null) // Clear session
    }

    fun isLoggedIn(): Boolean = prefs.getCurrentUser() != null

    fun register(user: UserInfo): Boolean {
        if (registeredUsers.any { it.username == user.username }) {
            return false // Registration failed
        }

        registeredUsers.add(user)
        prefs.saveUsers(registeredUsers)
        return true
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

    fun saveAllEntries() {
        prefs.saveEntries(timeEntries)
    }

    // In DataRepository object
    var activeTimerStart: Long = 0L // 0 means no timer running
    var activeProject: String? = null

    fun startTimer(projectName: String) {
        activeTimerStart = System.currentTimeMillis()
        activeProject = projectName
        // Optional: save to prefs so it survives reboot
    }

    fun stopTimer() {
        activeTimerStart = 0L
        activeProject = null
    }
}