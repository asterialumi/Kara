package lumi.projects.kara.screens.stats

interface StatsContract {
    interface View {
        fun showTotalTime(formattedTime: String)
        fun showProjectStats(stats: List<StatModel>)
        fun showTagStats(stats: List<StatModel>)
    }
    interface Presenter {
        fun start()
    }
}