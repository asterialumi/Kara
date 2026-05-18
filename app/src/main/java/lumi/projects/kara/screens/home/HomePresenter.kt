package lumi.projects.kara.screens.home

import android.app.Activity
import lumi.projects.kara.R

class HomePresenter(private val view: HomeContract.View): HomeContract.Presenter {
    override fun onLogoutButtonClicked() {
        //reset state (unloads all time entries)
        //will do logic once database is implemented

        view.navigateToLoginScreen()
    }

    override fun startTimer() {
        //TODO: initiates logic for timer
    }

    override fun endTimer() {
        //TODO: stops timer, prompts for editing entry, and saves time entry
    }

}