package client.contracts

import javafx.scene.control.Label
import shared.IBasePane
import shared.IBasePresenter

interface IFilesContract {

    interface IView: IBasePane {
        fun showFilesList(files: Array<String>)
        fun onListItemClicked(listItem: Label)
        fun showDownloadedAlert()
        fun showNoFilesFoundedAlert()
        fun showNoFileSelectedAlert()
        fun showConnectionErrorAlert()
    }

    interface IPresenter: IBasePresenter<IView> {
        fun onSendFilesToServerClicked()
        fun onSearchClicked()
        fun onDownloadClicked()
        fun onGetFilesFromServer()
    }
}