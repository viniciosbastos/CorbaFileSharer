package client.ui

import client.contracts.IMainContract
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.stage.Stage

class MainStage: Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.title = "FileSharer Client"
        primaryStage.scene = MainPresenter().createScene()
        primaryStage.show()
    }

    private class MainScene: IMainContract.IView {
        override fun render(): Scene {
            val group = Group()
            return Scene(group, 1200.0, 600.0)
        }

    }

    private class MainPresenter: IMainContract.IPresenter {
        override val view: IMainContract.IView
        init {
            view = MainScene()
        }
    }
}