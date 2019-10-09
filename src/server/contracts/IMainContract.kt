package server.contracts

import javafx.scene.Scene
import javafx.scene.layout.Pane
import shared.IBaseScene

interface IMainContract {

    interface IScene: IBaseScene {
    }

    interface IPresenter{
        val view: IScene

        fun setListeners()
        fun renderScene(): Scene {
            val scene = view.render()
            setListeners()
            return scene
        }
    }
}