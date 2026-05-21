package lumi.projects.kara.data.repository

import lumi.projects.kara.data.model.UserInfo

object UserRepository {
    private val users = mutableListOf<UserInfo>()
    //a fake database for now
    //this does not persist after app closes

    fun registerUser(username: String, password: String): Boolean {
        if (users.any { it.username == username }) return false
        users.add(UserInfo(username, password))
        return true
    }

    fun validateUser(username: String, password: String): UserInfo? {
        return users.find {
            it.username == username && it.password == password
        }
    }
}