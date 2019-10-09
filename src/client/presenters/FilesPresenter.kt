package client.presenters

import client.contracts.IFilesContract
import client.interactors.IRemoteFilesInteractor
import client.ui.FilesPane

class FilesPresenter
    constructor(private val interactor: IRemoteFilesInteractor)
    : IFilesContract.IPresenter {
    override val view = FilesPane()

    override fun setListeners() {
        view.updateLocalButton.setOnMouseClicked { updateLocalList() }
        view.updateRemoteButton.setOnMouseClicked { sendFilesToServer() }
    }

    override fun sendFilesToServer() {
        interactor.updateRemote()
    }

    override fun updateLocalList() {
        val files = interactor.updateLocal()
    }
}