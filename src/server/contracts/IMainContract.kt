package server.contracts

import javafx.scene.Scene
import javafx.scene.layout.Pane
import server.db.Entry
import shared.IBaseScene

interface IMainContract {

    interface IScene: IBaseScene {
        fun addEntries(entries: List<Entry>)
        fun addEntry(entry: Entry)
        fun showClientsFiles(index: Int)
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