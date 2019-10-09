package shared

import javafx.scene.layout.Pane

interface IBasePresenter<T: IBasePane> {

    val view: T

    fun setListeners()
    fun render(): Pane {
        val scene = view.render()
        setListeners()
        return scene
    }
}