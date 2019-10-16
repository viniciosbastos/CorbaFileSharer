package client.ui

import client.contracts.IMainContract
import client.interactors.FilesInteractor
import client.presenters.FilesPresenter
import client.presenters.SettingsPresenter
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.stage.Stage
import javafx.stage.Window

class MainStage: Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.apply {
            title = "FileSharer Client"
            scene = MainPresenter(primaryStage).renderScene()
            show()
        }
    }

    open class MainScene: IMainContract.IScene {
        private lateinit var rootElement: GridPane
        private lateinit var navPane: Pane

        lateinit var titleLabel: Label
        lateinit var settingsLabel: Label

        private fun createRootElement() {
            rootElement = GridPane().apply {
                rowConstraints.addAll(RowConstraints(50.0), RowConstraints(550.0))
            }
        }

        private fun addHeader() {
            titleLabel = Label("FileSharer").apply { styleClass.addAll("fs-title", "fs-label-padding") }
            settingsLabel = Label("Settings").apply { styleClass.addAll("fs-title", "fs-label-padding") }
            val box = BorderPane().apply {
                left = titleLabel
                right = settingsLabel
            }
            rootElement.add(box, 0, 0)
        }

        private fun addNavPane() {
            navPane = StackPane().apply { styleClass.add("fs-full-width") }
            rootElement.add(navPane, 0, 1)
        }

        override fun render(): Scene {
            createRootElement()
            addHeader()
            addNavPane()
            return Scene(rootElement, 1200.0, 600.0).apply { stylesheets.add("client/ui/styles/main.css") }
        }

        override fun showSettingsView(settings: Pane) {
            changePane(settings)
        }

        override fun showHomeView(home: Pane) {
            changePane(home)
        }

        private fun changePane(pane: Pane) {
            navPane.children.clear()
            navPane.children.add(pane)
        }
    }

    private class MainPresenter constructor(private val owner: Window): IMainContract.IPresenter {
        override val view = MainScene()
        private var settingsPresenter: SettingsPresenter? = null
        private var filesPresenter: FilesPresenter? = null

        init {
//            ServerProtocol.get().synchronize()
        }

        override fun setListeners() {
            view.titleLabel.setOnMouseClicked { showHomeView() }
            view.settingsLabel.setOnMouseClicked { showSettingsView() }
        }

        override fun showSettingsView() {
            if (settingsPresenter == null)
                settingsPresenter = SettingsPresenter(owner)

            settingsPresenter?.let { view.showSettingsView(it.render()) }
        }

        override fun showHomeView() {
            if (filesPresenter == null)
                filesPresenter = FilesPresenter(FilesInteractor())

            filesPresenter?.let { view.showHomeView(it.view) }
        }

        override fun renderScene(): Scene {
            val scene = super.renderScene()
            showHomeView()
            return scene
        }
    }
}