package lumi.projects.kara.data.model

import com.google.gson.annotations.SerializedName

data class TimeEntry(
    val id: String = java.util.UUID.randomUUID().toString(),
    val projectName: String = "Unnamed",
    val description: String = "",
    val startTime: Long,
    val endTime: Long,
    @SerializedName("tags") val tags: List<String> = mutableListOf()
) {
    val durationMillis: Long get() = endTime - startTime
}