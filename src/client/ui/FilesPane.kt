package client.ui

import client.contracts.IFilesContract
import javafx.application.Platform
import javafx.scene.control.*
import javafx.scene.layout.*
import shared.adapters.FilesAdapter

class FilesPane: IFilesContract.IView{
    override val root: Pane = Pane()

    val downloadButton = Button("Download").apply { styleClass.add("fs-custom-button") }
    val updateLocalButton = Button("Get Files from Server").apply { styleClass.add("fs-custom-button") }
    val updateRemoteButton = Button("Send Files to Server").apply { styleClass.add("fs-custom-button") }
    val searchButton = Button("Search").apply { styleClass.add("fs-custom-button") }

    val searchField = TextField().apply {
        promptText = "Name/Extension to search"
    }
    private val scrollPane = ScrollPane()
    private val filesAdapter = FilesAdapter(this::onListItemClicked).apply { styleClass.addAll("fs-list", "fs-full-width") }
    var selectedFile: Label? = null

    init {
        scrollPane.apply {
            content = filesAdapter
            vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
            hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        }

        val box = VBox()
        val header = BorderPane().apply {
            left = addSearchBox()
            right = addButtonsBox()
            styleClass.add("fs-full-width")
        }
        box.children.addAll(header, scrollPane)
        root.children.add(box)
    }

    private fun addSearchBox(): HBox {
        return HBox().apply {
            children.addAll(searchField, searchButton)
            styleClass.add("fs-search-box")
        }
    }

    private fun addButtonsBox(): HBox {
        return HBox().apply {
            children.addAll(downloadButton, updateLocalButton, updateRemoteButton)
            styleClass.add("fs-button-box")
        }
    }

    override fun render(): Pane {
        return root
    }

    override fun showFilesList(files: Array<String>) {
        Platform.runLater { filesAdapter.submitList(files.toList()) }
    }

    override fun onListItemClicked(listItem: Label) {
        selectedFile?.let { it.styleClass.remove("fs-active") }
        selectedFile = listItem
        listItem.styleClass.add("fs-active")
    }

    override fun showDownloadedAlert() {
        Alert(Alert.AlertType.INFORMATION).apply {
            title = "Download"
            headerText = null
            contentText = "File downloaded."
            showAndWait()
        }
    }

    override fun showNoFilesFoundedAlert() {
        Alert(Alert.AlertType.INFORMATION).apply {
            title = "Files Loaded"
            headerText = null
            contentText = "No files founded."
            showAndWait()
        }
    }

    override fun showNoFileSelectedAlert() {
        Alert(Alert.AlertType.WARNING).apply {
            title = "No File Selected"
            headerText = null
            contentText = "No File was selected. Please choose one before trying to download."
            showAndWait()
        }
    }

    override fun showConnectionErrorAlert() {
        Alert(Alert.AlertType.WARNING).apply {
            title = "Connection Error"
            contentText = "Error trying to establish connection."
            showAndWait()
        }
    }
}