package lumi.projects.kara.screens.appmain

import lumi.projects.kara.data.repository.DataRepository

class AppMainModel : AppMainContract.Model {
    override fun isLoggedIn() = DataRepository.isLoggedIn()
    override fun getExportJson() = DataRepository.exportToClipboardJson()
    override fun importJson(json: String) = DataRepository.importFromClipboardJson(json)
}