package lumi.projects.kara.screens.home

import lumi.projects.kara.data.repository.DataRepository

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {

    override fun start() {
        // Fetch from Repository and tell view to show them
        // These will be reused if we need to refresh the list
        view.displayProjects(DataRepository.projects)
        view.displayTags(DataRepository.tags)
    }

    override fun onAddProjectClicked(name: String) {
        DataRepository.addProject(name)
        view.displayProjects(DataRepository.projects)
    }

    override fun onAddTagClicked(name: String) {
        DataRepository.addTag(name)
        view.displayTags(DataRepository.tags)
    }

    override fun onLogoutClicked() {
        DataRepository.logout()
        view.navigateToLogin()
    }
}