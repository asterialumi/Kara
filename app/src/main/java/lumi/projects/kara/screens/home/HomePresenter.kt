package lumi.projects.kara.screens.home

class HomePresenter(
    private val view: HomeContract.View,
    private val model: HomeContract.Model
) : HomeContract.Presenter {

    override fun start() {
        // Fetch from Model and tell view to show them
        // These will be reused if we need to refresh the list
        view.displayProjects(model.getProjects())
        view.displayTags(model.getTags())
    }

    override fun onAddProjectClicked(name: String) {
        model.addProject(name)
        view.displayProjects(model.getProjects())
    }

    override fun onAddTagClicked(name: String) {
        model.addTag(name)
        view.displayTags(model.getTags())
    }

    override fun onLogoutClicked() {
        model.logout()
        view.navigateToLogin()
    }
}