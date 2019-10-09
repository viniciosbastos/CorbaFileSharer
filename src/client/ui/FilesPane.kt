package client.ui

import client.contracts.IFilesContract
import javafx.scene.control.Button
import javafx.scene.layout.Pane

class FilesPane: IFilesContract.IView{
    override val root: Pane = Pane()

    lateinit var loadButton: Button

    fun addLoadButton() {
        loadButton = Button("Load")
        root.children.add(loadButton)
    }

    override fun render(): Pane {
        addLoadButton()
        return root
    }
}