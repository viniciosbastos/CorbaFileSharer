package client.presenters

import client.UserPreferences
import client.contracts.ISettingsContract
import client.ui.SettingsPane
import javafx.stage.Window

class SettingsPresenter constructor(private val owner: Window): ISettingsContract.IPresenter {
    override val view = SettingsPane(owner)

    override fun setListeners() {
        view.searchButton.setOnMouseClicked { chooseFolder() }
    }

    override fun chooseFolder() {
        val file = view.chooseFolder()
        view.showFolderPath(file.absolutePath)
        UserPreferences.get().sharedFolder = file.absolutePath
    }
}