package lumi.projects.kara.app

import android.app.Application
import lumi.projects.kara.data.model.UserInfo
import lumi.projects.kara.data.repository.DataRepository

class KaraApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // Repository is initialized here before any activity starts
        // If database gets utilized, preparations also go here
        DataRepository.init(this)
    }
}