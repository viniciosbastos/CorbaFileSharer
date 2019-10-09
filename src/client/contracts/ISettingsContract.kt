package client.contracts

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