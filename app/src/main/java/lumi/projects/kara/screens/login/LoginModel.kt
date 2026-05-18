package lumi.projects.kara.screens.login

class LoginModel {
    private val username = "asteria"
    private val password = "123"

    fun login(username: String, password: String): Boolean {
        return username.equals(this.username, false)
            && password.equals(this.password, false)
    }
}