package lumi.projects.kara.screens.home

interface HomeContract {
    interface View {
        fun displayProjects(projects: List<String>)
        fun displayTags(tags: List<String>)
        fun navigateToLogin()
    }

    interface Presenter {
        fun start() // Called when fragment opens
        fun onAddProjectClicked(name: String)
        fun onAddTagClicked(name: String)
        fun onLogoutClicked()
    }
}