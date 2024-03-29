package client.contracts

import javafx.scene.Scene
import javafx.scene.layout.Pane
import shared.IBaseScene

interface IMainContract {

    interface IScene: IBaseScene {
        fun showSettingsView(settings: Pane)
        fun showHomeView(home: Pane)
    }

    interface IPresenter{
        val view: IScene

        fun setListeners()
        fun renderScene(): Scene {
            val scene = view.render()
            setListeners()
            return scene
        }
        fun showSettingsView()
        fun showHomeView()
    }
}