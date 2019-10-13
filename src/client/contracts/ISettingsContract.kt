package client.contracts

import shared.IBasePane
import shared.IBasePresenter
import java.io.File

interface ISettingsContract {

    interface IView: IBasePane {
        fun chooseFolder(): File?
        fun showFolderPath(path: String)
        fun showSavedAlert()
        fun showIncompleteInfoAlert()
    }

    interface IPresenter: IBasePresenter<IView> {
        fun chooseFolder()
        fun save()
    }
}