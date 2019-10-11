package client.ui

import client.UserPreferences
import client.contracts.ISettingsContract
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.*
import javafx.stage.DirectoryChooser
import javafx.stage.Window
import java.io.File

class SettingsPane constructor(private val owner: Window): ISettingsContract.IView {
    override val root = VBox().apply { styleClass.add("fs-grid") }
    val username = TextField()
    val folderPath = TextField().apply {
        isEditable = false
        promptText = "Click to select shared folder"
    }
    val saveButton = Button("Save").apply { styleClass.add("fs-custom-button") }

    init {
        loadUserPreferences()
        root.children.addAll(
            Label("Nome do Usuário"),
            username,
            Label("Pasta Compartilhada"),
            folderPath,
            saveButton
        )
    }

    private fun loadUserPreferences() {
        val userPref = UserPreferences.get()
        username.text = userPref.username
        folderPath.text = userPref.sharedFolder
    }

    override fun render(): Pane {
        return root
    }

    override fun chooseFolder(): File? {
        return DirectoryChooser().showDialog(owner)
    }

    override fun showFolderPath(path: String) {
        folderPath.text = path
    }
}