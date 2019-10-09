package server.ui

import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.RowConstraints
import server.contracts.IMainContract

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
        val box = HBox()
        titleLabel = Label("FileSharer")
        settingsLabel = Label("Settings")
        box.children.addAll(titleLabel, settingsLabel)
        rootElement.add(box, 0, 0)
    }

    private fun addNavPane() {
        navPane = Pane()
        rootElement.add(navPane, 0, 1)
    }

    override fun render(): Scene {
        createRootElement()
        addHeader()
        addNavPane()
        return Scene(rootElement, 1200.0, 600.0).apply { stylesheets.add("client/ui/styles/main.css") }
    }

}