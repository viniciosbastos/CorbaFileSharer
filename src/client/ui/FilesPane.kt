package client.ui

import client.contracts.IFilesContract
import javafx.collections.FXCollections
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane

class FilesPane: IFilesContract.IView{
    override val root: Pane = Pane()

    lateinit var updateLocalButton: Button
    lateinit var updateRemoteButton: Button

    private val scrollPane = ScrollPane()
    private val listView = ListView<String>()
    private val filesList = FXCollections.observableArrayList<String>()

    init {
        listView.items = filesList
        scrollPane.content = listView
    }

    private fun addButtons() {
        updateLocalButton = Button("Update Local")
        updateRemoteButton = Button("Update Remote")
        root.children.add(HBox().apply { children.addAll(updateLocalButton, updateRemoteButton) })
    }

    private fun addListView() {
        root.children.add(scrollPane)
    }

    override fun render(): Pane {
        addListView()
        addButtons()
        return root
    }

    override fun showFilesList(files: Array<String>) {
        filesList.clear()
        filesList.addAll(files)
    }
}