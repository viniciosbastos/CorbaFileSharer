package client.ui

import client.contracts.ISettingsContract
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.stage.DirectoryChooser
import javafx.stage.Window
import java.io.File

open class SettingsPane constructor(private val owner: Window): ISettingsContract.IView {
    override val root: Pane = Pane()
    lateinit var folderPath: TextField
    lateinit var searchButton: Button

    private fun addFolderForm() {
        val grid = GridPane().apply { add(Label("Pasta Compartilhada"),0, 0) }

        val box = HBox()
        folderPath = TextField()
        folderPath.isEditable = false
        searchButton = Button("...")
        box.children.addAll(folderPath, searchButton)
        grid.add(box, 0, 1)
        root.children.add(grid)
    }

    override fun render(): Pane {
        addFolderForm()
        return root
    }

    override fun chooseFolder(): File {
        val fileChooser = DirectoryChooser()

        return fileChooser.showDialog(owner)
    }

    override fun showFolderPath(path: String) {
        folderPath.text = path
    }
}