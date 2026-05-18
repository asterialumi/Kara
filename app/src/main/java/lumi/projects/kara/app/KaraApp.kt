package lumi.projects.kara.app

import android.app.Application
import lumi.projects.kara.data.UserInfo

class KaraApp: Application() {

    private var userInfo = UserInfo()

    fun getUserInfo() = userInfo

    fun setUserInfo(userInfo: UserInfo) {
        this.userInfo = userInfo
    }
}