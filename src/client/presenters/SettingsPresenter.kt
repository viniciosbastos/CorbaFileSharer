package client.presenters

import client.UserPreferences
import client.contracts.ISettingsContract
import client.ui.SettingsPane
import javafx.stage.Window

class SettingsPresenter constructor(private val owner: Window): ISettingsContract.IPresenter {
    override val view = SettingsPane(owner)

    override fun setListeners() {
        view.folderPath.setOnMouseClicked { chooseFolder() }
        view.saveButton.setOnMouseClicked { save() }
    }

    override fun chooseFolder() {
        val file = view.chooseFolder()
        view.showFolderPath(file.absolutePath)
    }

    override fun save() {
        UserPreferences.get().apply {
            sharedFolder = view.folderPath.text
            username = view.username.text
        }

    }
}