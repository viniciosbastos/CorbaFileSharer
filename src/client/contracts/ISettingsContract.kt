package client.contracts

import shared.IBasePane
import shared.IBasePresenter
import java.io.File

interface ISettingsContract {

    interface IView: IBasePane {
        fun chooseFolder(): File
        fun showFolderPath(path: String)
    }

    interface IPresenter: IBasePresenter<IView> {
        fun chooseFolder()
    }
}