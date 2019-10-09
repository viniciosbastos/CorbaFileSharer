package client.contracts

import javafx.scene.layout.Pane

interface IBasePane {
    val root: Pane
    fun render(): Pane
}