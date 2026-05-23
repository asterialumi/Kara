package lumi.projects.kara.screens.home

import lumi.projects.kara.data.repository.DataRepository

class HomeModel : HomeContract.Model {
    override fun getProjects(): List<String> = DataRepository.projects

    override fun getTags(): List<String> = DataRepository.tags

    override fun addProject(name: String) = DataRepository.addProject(name)

    override fun addTag(name: String) = DataRepository.addTag(name)

    override fun logout() = DataRepository.logout()
}