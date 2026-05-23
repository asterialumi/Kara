package lumi.projects.kara.screens.login

import lumi.projects.kara.data.repository.DataRepository

class LoginModel : LoginContract.Model {
    override fun attemptLogin(username: String, password: String): Boolean {
        return DataRepository.login(username, password)
    }
}