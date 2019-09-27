package client.contracts

import javafx.scene.Scene

interface IBasePresenter<T: IBaseView> {

    val view: T

    fun createScene() = view.render()
}