package client.presenters

import client.contracts.IFilesContract
import client.interactors.IRemoteFilesInteractor
import client.ui.FilesPane

class FilesPresenter
    constructor(private val interactor: IRemoteFilesInteractor)
    : IFilesContract.IPresenter {
    override val view = FilesPane()

    override fun setListeners() {
        view.downloadButton.setOnMouseClicked { onDownloadClicked() }
        view.updateLocalButton.setOnMouseClicked { onUpdateLocalClicked() }
        view.updateRemoteButton.setOnMouseClicked { onSendFilesToServerClicked() }
    }

    override fun onSendFilesToServerClicked() {
        interactor.updateRemote()
    }

    override fun onUpdateLocalClicked() {
        val files = interactor.updateLocal()
        view.showFilesList(files)
    }

    override fun onDownloadClicked() {
        val selectedFile = view.selectedFile!!.text
        interactor.downloadFile(selectedFile)
    }
}