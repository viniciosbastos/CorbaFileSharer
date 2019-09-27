package client.contracts

import javafx.scene.Scene

interface IBasePresenter<T: IBaseView> {

    val view: T

    fun setListeners()
    fun createScene(): Scene {
        setListeners()
        return view.render()
    }
}