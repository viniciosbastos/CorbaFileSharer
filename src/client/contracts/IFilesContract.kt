package client.contracts

import javafx.scene.control.Label
import shared.IBasePane
import shared.IBasePresenter

interface IFilesContract {

    interface IView: IBasePane {
        fun showFilesList(files: Array<String>)
        fun onListItemClicked(listItem: Label)
        fun showDownloadedAlert()
        fun showFilesLoadedAlert()
        fun showNoFileSelectedAlert()
        fun showConnectionErrorAlert()
    }

    interface IPresenter: IBasePresenter<IView> {
        fun onSendFilesToServerClicked()
        fun getFilesFromServer()
        fun onDownloadClicked()
    }
}