package client.presenters

import client.contracts.IFilesContract
import client.interactors.IRemoteFilesInteractor
import client.ui.FilesPane
import org.omg.CORBA.COMM_FAILURE
import java.net.ConnectException

class FilesPresenter
    constructor(private val interactor: IRemoteFilesInteractor)
    : IFilesContract.IPresenter {

    override val view = FilesPane()

    override fun setListeners() {
        view.downloadButton.setOnMouseClicked { onDownloadClicked() }
        view.updateLocalButton.setOnMouseClicked { getFilesFromServer() }
        view.updateRemoteButton.setOnMouseClicked { onSendFilesToServerClicked() }
    }

    override fun onSendFilesToServerClicked() {
        try {
            interactor.updateRemote()
        } catch (ex: COMM_FAILURE) {
            view.showConnectionErrorAlert()
        }
    }

    override fun getFilesFromServer() {
        try {
            val files = interactor.updateLocal()
            view.showFilesList(files)
            view.showFilesLoadedAlert()
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
}