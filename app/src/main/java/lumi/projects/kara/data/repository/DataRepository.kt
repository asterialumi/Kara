package lumi.projects.kara.data.repository

import android.content.Context
import lumi.projects.kara.data.local.SerializeManager
import lumi.projects.kara.data.model.TimeEntry
import lumi.projects.kara.data.model.UserInfo

object DataRepository {
    private lateinit var prefs: SerializeManager

    // Global App Data
    private var registeredUsers = mutableListOf<UserInfo>()

    // Using Mutable Lists as an alternative to a database
    var projects = mutableListOf<String>()
    var tags = mutableListOf<String>()
    var timeEntries = mutableListOf<TimeEntry>()

    // Timer States
    var activeTimerStart: Long = 0L
    var activeProject: String? = null
    var accumulatedMillis: Long = 0L
    var activeDescription: String = ""
    var activeTagsInput: String = ""

    fun init(context: Context) {
        // Initial Preparations
        prefs = SerializeManager(context)
        registeredUsers = prefs.getUsers()

        val savedUser = prefs.getCurrentUser()
        if (savedUser != null) {
            setupUser(savedUser)
        }
    }

    fun setupUser(username: String) {
        prefs.initUserSession(username)
        projects = prefs.getProjects()
        tags = prefs.getTags()
        timeEntries = prefs.getEntries()

        // Persistent Timer
        activeTimerStart = prefs.getActiveTimerStart()
        activeProject = prefs.getActiveProject()
        accumulatedMillis = prefs.getAccumulatedMillis()
        activeDescription = prefs.getActiveDescription()
        activeTagsInput = prefs.getActiveTagsInput()
    }


    // FOR USERS
    fun login(username: String, pword: String): Boolean {
        val user = registeredUsers.find { it.username == username && it.password == pword }
        if (user != null) {
            prefs.saveSession(username)
            setupUser(username)
            return true
        }
        return false
    }

    fun logout() {
        prefs.saveSession(null)

        // Clear memory lists
        projects = mutableListOf()
        tags = mutableListOf()
        timeEntries = mutableListOf()

        // Clear timer state
        activeTimerStart = 0L
        activeProject = null
        accumulatedMillis = 0L
        activeDescription = ""
        activeTagsInput = ""
    }

    fun isLoggedIn(): Boolean = prefs.getCurrentUser() != null
    fun getLoggedInUser(): String = prefs.getCurrentUser() ?: "User"

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
        accumulatedMillis = 0L
        saveTimerState()
    }

    fun pauseTimer(currentElapsed: Long) {
        accumulatedMillis = currentElapsed
        activeTimerStart = 0L
        saveTimerState()
    }

    fun resumeTimer() {
        activeTimerStart = System.currentTimeMillis()
        saveTimerState()
    }

    fun stopTimer() {
        activeTimerStart = 0L
        activeProject = null
        accumulatedMillis = 0L
        saveTimerState()
    }

    fun updateTimerMetadata(desc: String, tags: String) {
        activeDescription = desc
        activeTagsInput = tags
        saveTimerState()
    }

    private fun saveTimerState() {
        prefs.saveActiveTimer(activeTimerStart, activeProject, accumulatedMillis, activeDescription, activeTagsInput)
    }

    // FOR EXPORT/IMPORT
    fun exportToClipboardJson(): String {
        val dataMap = mapOf(
            "projects" to projects,
            "tags" to tags,
            "entries" to timeEntries
        )
        return com.google.gson.Gson().toJson(dataMap)
    }

    fun importFromClipboardJson(json: String): Boolean {
        return try {
            val type = object : com.google.gson.reflect.TypeToken<Map<String, Any>>() {}.type
            val data: Map<String, Any> = com.google.gson.Gson().fromJson(json, type)

            val gson = com.google.gson.Gson()

            // Deserialize and Update Lists
            projects = gson.fromJson(gson.toJson(data["projects"]), object : com.google.gson.reflect.TypeToken<MutableList<String>>() {}.type)
            tags = gson.fromJson(gson.toJson(data["tags"]), object : com.google.gson.reflect.TypeToken<MutableList<String>>() {}.type)
            timeEntries = gson.fromJson(gson.toJson(data["entries"]), object : com.google.gson.reflect.TypeToken<MutableList<TimeEntry>>() {}.type)

            // Force save to the current user's disk
            prefs.saveProjects(projects)
            prefs.saveTags(tags)
            prefs.saveEntries(timeEntries)
            true
        } catch (e: Exception) {
            false
        }
    }
}