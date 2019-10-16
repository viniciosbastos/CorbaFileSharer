package client.presenters

import client.UserPreferences
import client.contracts.IFilesContract
import client.interactors.IRemoteFilesInteractor
import client.ui.FilesPane
import org.omg.CORBA.COMM_FAILURE
import java.io.File

class FilesPresenter
    constructor(private val interactor: IRemoteFilesInteractor)
    : IFilesContract.IPresenter {

    override val view = FilesPane()

    init{
        setListeners()
    }

    override fun setListeners() {
        view.getDownloadButton().setOnMouseClicked { onDownloadClicked() }
        view.getSearchButton().setOnMouseClicked { onSearchClicked() }
        view.getReloadServerButton().setOnMouseClicked { onUpdateClicked() }
        view.getReloadLocalButton().setOnMouseClicked { onUpdateAndSendClicked() }
    }

    override fun onUpdateAndSendClicked() {
        val usrPref = UserPreferences.get()
        val files = File(usrPref.sharedFolder).list()
        view.showMyFilesList(files)
        sendFilesToServer()
    }

    override  fun onUpdateClicked() {
        try {
            val files = interactor.getFilesFromServer()
            view.showServerFilesList(files)
            if (files.isEmpty())
                view.showNoFilesFoundedAlert()
        } catch (ex: COMM_FAILURE) {
            view.showConnectionErrorAlert()
        }
    }

    private fun sendFilesToServer() {
        try {
            interactor.updateRemote()
        } catch (ex: COMM_FAILURE) {
            view.showConnectionErrorAlert()
        }
    }

    override fun onDownloadClicked() {
        val selectedFiles = view.getSelectedFiles()
        if (selectedFiles.isNotEmpty()) {
            view.showTransferFiles()
            selectedFiles.forEachIndexed { index, fileName ->
                view.showDownloadingFile(index)
                download(fileName)
                view.showConcludedFile(index)
            }
            view.clearSelectedFiles()
        }
        else {
            view.showNoFileSelectedAlert()
        }
    }

    private fun download(fileName: String) {
            try {
                interactor.downloadFile(fileName)
            } catch (ex: COMM_FAILURE) {
                view.showConnectionErrorAlert()
            }
    }

    override fun onSearchClicked() {
        val toSearch = view.getSearchFieldText()
        try {
            val files = interactor.getFilesFromServer(toSearch)
            view.showServerFilesList(files)
            if (files.isEmpty())
                view.showNoFilesFoundedAlert()
        } catch (ex: COMM_FAILURE) {
            view.showConnectionErrorAlert()
        }
    }
}