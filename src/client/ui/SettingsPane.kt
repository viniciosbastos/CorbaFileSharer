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
    lateinit var username: TextField
    lateinit var folderPath: TextField
    lateinit var searchButton: Button
    lateinit var saveButton: Button

    private fun addForm() {
        val grid = GridPane()
        username = TextField()
        val box = HBox()
        folderPath = TextField()
        folderPath.isEditable = false
        searchButton = Button("...")
        saveButton = Button("Save")

        box.children.addAll(folderPath, searchButton)
        grid.apply{
            add(Label("Nome do Usuário"),0, 0)
            add(username, 0, 1)
            add(Label("Pasta Compartilhada"),0, 2)
            add(box, 0, 3)
            add(saveButton, 0, 4)
        }
        root.children.add(grid)
    }

    override fun render(): Pane {
        addForm()
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