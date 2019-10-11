package client.ui

import client.contracts.IFilesContract
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import shared.adapters.FilesAdapter

class FilesPane: IFilesContract.IView{
    override val root: Pane = Pane()

    lateinit var updateLocalButton: Button
    lateinit var updateRemoteButton: Button

    private val scrollPane = ScrollPane()
    private val filesAdapter = FilesAdapter().apply { styleClass.add("fs-list") }

    init {
        scrollPane.apply {
            content = filesAdapter
            vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
            hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        }

        val box = VBox()
        box.children.addAll(addButtons(), scrollPane)
        root.children.add(box)
    }

    private fun addButtons(): HBox {
        updateLocalButton = Button("Update Local")
        updateRemoteButton = Button("Update Remote")
        return HBox().apply { children.addAll(updateLocalButton, updateRemoteButton) }
    }

    override fun render(): Pane {
        return root
    }

    override fun showFilesList(files: Array<String>) {
        Platform.runLater { filesAdapter.submitList(files.toList()) }
    }
}