package lumi.projects.kara.screens.register

import lumi.projects.kara.data.model.UserInfo
import lumi.projects.kara.data.repository.DataRepository

class RegisterModel : RegisterContract.Model {
    override fun registerUser(username: String, pword: String): Boolean {
        // Model handles the creation of the UserInfo object
        return DataRepository.register(UserInfo(username, pword))
    }
}