package server.ui

import server.contracts.IMainContract
import client.presenters.SettingsPresenter
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.RowConstraints
import javafx.stage.Stage
import javafx.stage.Window
import server.presenters.MainPresenter

class MainStage: Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.apply {
            title = "FileSharer Server"
            scene = MainPresenter(primaryStage).renderScene()
            show()
        }
    }



}