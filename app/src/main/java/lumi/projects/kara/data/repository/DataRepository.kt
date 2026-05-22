package lumi.projects.kara.data.repository

import android.content.Context
import lumi.projects.kara.data.local.SerializeManager
import lumi.projects.kara.data.model.TimeEntry
import lumi.projects.kara.data.model.UserInfo

object DataRepository {
    private lateinit var prefs: SerializeManager

    // Using Mutable Lists as an alternative to a database
    var projects = mutableListOf<String>()
    var tags = mutableListOf<String>()
    var timeEntries = mutableListOf<TimeEntry>()
    private var registeredUsers = mutableListOf<UserInfo>()

    // Timer States
    var activeTimerStart: Long = 0L
    var activeProject: String? = null

    fun init(context: Context) {
        // Initial Preparations
        prefs = SerializeManager(context)
        projects = prefs.getProjects()
        tags = prefs.getTags()
        timeEntries = prefs.getEntries()
        registeredUsers = prefs.getUsers()

        // Persistent Timer
        activeTimerStart = prefs.getActiveTimerStart()
        activeProject = prefs.getActiveProject()
    }


    // FOR USERS
    fun login(username: String, pword: String): Boolean {
        val user = registeredUsers.find { it.username == username && it.password == pword }
        if (user != null) {
            prefs.saveSession(username)
            return true
        }
        return false
    }

    fun logout() {
        prefs.saveSession(null)
    }

    fun isLoggedIn(): Boolean = prefs.getCurrentUser() != null

    fun register(user: UserInfo): Boolean {
        if (registeredUsers.any { it.username == user.username }) {
            return false
        }

        registeredUsers.add(user)
        prefs.saveUsers(registeredUsers)
        return true
    }


    // FOR PROJECTS
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

    // FOR TIMER STATE
    fun startTimer(projectName: String) {
        activeTimerStart = System.currentTimeMillis()
        activeProject = projectName
        prefs.saveActiveTimer(activeTimerStart, activeProject)
    }

    fun stopTimer() {
        activeTimerStart = 0L
        activeProject = null
        prefs.saveActiveTimer(0L, null)
    }
}