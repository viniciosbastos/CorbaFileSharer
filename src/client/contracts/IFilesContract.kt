package client.contracts

import shared.IBasePane
import shared.IBasePresenter

interface IFilesContract {

    interface IView: IBasePane {
        fun showFilesList(files: Array<String>)
    }

    interface IPresenter: IBasePresenter<IView> {
        fun sendFilesToServer()
        fun updateLocalList()
    }
}