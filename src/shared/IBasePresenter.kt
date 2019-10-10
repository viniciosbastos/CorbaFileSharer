package shared

import javafx.scene.layout.Pane

interface IBasePresenter<T: IBasePane> {

    val view: T

    fun setListeners()
    fun render(): Pane {
        val pane = view.render()
        setListeners()
        return pane
    }
}