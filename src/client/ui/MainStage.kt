package client.ui

import client.contracts.IMainContract
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.layout.RowConstraints
import javafx.stage.Stage

class MainStage: Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.apply {
            title = "FileSharer Client"
            scene = MainPresenter().createScene()
            show()
        }
    }

    private class MainScene: IMainContract.IView {
        private lateinit var rootElement: GridPane
        private lateinit var navPane: Pane

        private fun createRootElement() {
            rootElement = GridPane().apply {
                rowConstraints.addAll(RowConstraints(50.0), RowConstraints(550.0))
            }
        }

        private fun createHeader() {
            val label = Label("FileSharer")
            label.setOnMouseClicked {  }
            rootElement.add(label, 0, 0)
        }

        private fun createNavPane() {
            navPane = Pane()
            navPane.children.add(Label("Nothing to show here."))
            rootElement.add(navPane, 0, 1)
        }

        override fun render(): Scene {
            createRootElement()
            createHeader()
            createNavPane()
            return Scene(rootElement, 1200.0, 600.0).apply { stylesheets.add("client/ui/styles/main.css") }
        }

    }

    private class MainPresenter: IMainContract.IPresenter {

        override val view = MainScene()

        override fun setListeners() {
//            TODO("Implement view listeners")
        }
    }
}