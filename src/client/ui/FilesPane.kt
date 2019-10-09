package client.ui

import client.contracts.IFilesContract
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane

class FilesPane: IFilesContract.IView{
    override val root: Pane = Pane()

    lateinit var updateLocalButton: Button
    lateinit var updateRemoteButton: Button

    private fun addButtons() {
        updateLocalButton = Button("Update Local")
        updateRemoteButton = Button("Update Remote")
        root.children.add(HBox().apply { children.addAll(updateLocalButton, updateRemoteButton) })
    }

    override fun render(): Pane {
        addButtons()
        return root
    }
}