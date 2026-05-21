package lumi.projects.kara.app

import android.app.Application
import lumi.projects.kara.data.model.UserInfo
import lumi.projects.kara.data.repository.DataRepository

class KaraApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // This is where the magic happens.
        // We initialize the repo here so it's ready for EVERY screen.
        DataRepository.init(this)
    }
}