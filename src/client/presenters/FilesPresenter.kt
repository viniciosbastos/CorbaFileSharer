package client.presenters

import client.contracts.IFilesContract
import client.ui.FilesPane

class FilesPresenter: IFilesContract.IPresenter {
    override val view = FilesPane()

    override fun setListeners() {
        view.updateLocalButton.setOnMouseClicked { updateLocal() }
        view.updateRemoteButton.setOnMouseClicked { updateRemote() }
    }

    override fun updateRemote() {

    }

    override fun updateLocal() {

    }
}