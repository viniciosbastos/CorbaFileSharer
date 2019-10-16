package client.contracts

import javafx.scene.control.Label
import shared.IBasePane
import shared.IBasePresenter

interface IFilesContract {

    interface IView: IBasePane {
        fun showTransferFiles()
        fun showMyFilesList(files: Array<String>)
        fun showServerFilesList(files: Array<String>)
        fun showNoFilesFoundedAlert()
        fun showNoFileSelectedAlert()
        fun showConnectionErrorAlert()
        fun showDownloadingFile(pos: Int)
        fun showConcludedFile(pos: Int)
        fun clearSelectedFiles()
    }

    interface IPresenter: IBasePresenter<IView> {
        fun onSearchClicked()
        fun onDownloadClicked()
        fun onUpdateClicked()
        fun onUpdateAndSendClicked()
    }
}