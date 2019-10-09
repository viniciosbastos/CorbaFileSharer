package server.presenters

import javafx.stage.Window
import server.contracts.IMainContract
import server.ui.MainScene
import server.ui.MainStage

class MainPresenter constructor(private val owner: Window): IMainContract.IPresenter {
    override val view = MainScene()

    override fun setListeners() {

    }
}
