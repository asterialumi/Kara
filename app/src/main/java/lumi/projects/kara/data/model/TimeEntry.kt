package lumi.projects.kara.data.model

data class TimeEntry(
    val id: String = java.util.UUID.randomUUID().toString(),
    val projectName: String,
    val description: String,
    val startTime: Long, // Use System.currentTimeMillis()
    val endTime: Long,
    val tags: List<String> = emptyList()
) {
    val durationMillis: Long get() = endTime - startTime
}