package client.presenters

import client.contracts.IFilesContract
import client.interactors.IRemoteFilesInteractor
import client.ui.FilesPane
import org.omg.CORBA.COMM_FAILURE

class FilesPresenter
    constructor(private val interactor: IRemoteFilesInteractor)
    : IFilesContract.IPresenter {

    override val view = FilesPane()

    override fun setListeners() {
        view.downloadButton.setOnMouseClicked { onDownloadClicked() }
        view.updateLocalButton.setOnMouseClicked { onGetFilesFromServer() }
        view.updateRemoteButton.setOnMouseClicked { onSendFilesToServerClicked() }
        view.searchButton.setOnMouseClicked { onSearchClicked() }
    }

    override fun onSendFilesToServerClicked() {
        try {
            interactor.updateRemote()
        } catch (ex: COMM_FAILURE) {
            view.showConnectionErrorAlert()
        }
    }

    override fun onGetFilesFromServer() {
        try {
            val files = interactor.getFilesFromServer()
            view.showFilesList(files)
            if (files.isEmpty())
                view.showNoFilesFoundedAlert()
        } catch (ex: COMM_FAILURE) {
            view.showConnectionErrorAlert()
        }
    }

    override fun onDownloadClicked() {
        val selectedFile = view.selectedFile
        if (selectedFile != null) {
            try {
                interactor.downloadFile(selectedFile.text)
                view.showDownloadedAlert()
            } catch (ex: COMM_FAILURE) {
                view.showConnectionErrorAlert()
            }
        }
        else {
            view.showNoFileSelectedAlert()
        }
    }

    override fun onSearchClicked() {
        val toSearch = view.searchField.text.trim()
        try {
            val files = interactor.getFilesFromServer(toSearch)
            view.showFilesList(files)
            if (files.isEmpty())
                view.showNoFilesFoundedAlert()
        } catch (ex: COMM_FAILURE) {
            view.showConnectionErrorAlert()
        }
    }
}