package client.presenters

import client.UserPreferences
import client.contracts.ISettingsContract
import client.services.P2PServerService
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
        file?.let { view.showFolderPath(it.absolutePath) }
    }

    override fun save() {
        val userPref = UserPreferences.get()
        userPref.apply {
            sharedFolder = view.folderPath.text
            username = view.username.text
        }
        if (userPref.isValid()) {
            P2PServerService.get().init()
            view.showSavedAlert()
        }
        else {
            view.showIncompleteInfoAlert()
        }
    }
}