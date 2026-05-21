package lumi.projects.kara.data.repository

object DataRepository {
    val projects = mutableListOf<String>("Work", "Study", "Exercise") // Dummy data
    val tags = mutableListOf<String>("Urgent", "Deep Work", "Relax")

    // We will add TimeEntries here later today
    val entries = mutableListOf<Any>()

    fun addProject(name: String) { projects.add(name) }
    fun addTag(name: String) { tags.add(name) }
}