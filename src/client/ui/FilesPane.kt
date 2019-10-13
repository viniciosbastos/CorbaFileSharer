package client.ui

import client.contracts.IFilesContract
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import shared.adapters.FilesAdapter

class FilesPane: IFilesContract.IView{
    override val root: Pane = Pane()

    lateinit var downloadButton: Button
    lateinit var updateLocalButton: Button
    lateinit var updateRemoteButton: Button

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
        box.children.addAll(addButtons(), scrollPane)
        root.children.add(box)
    }

    private fun addButtons(): HBox {
        downloadButton = Button("Download").apply { styleClass.add("fs-custom-button") }
        updateLocalButton = Button("Get Files from Server").apply { styleClass.add("fs-custom-button") }
        updateRemoteButton = Button("Send Files to Server").apply { styleClass.add("fs-custom-button") }
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

    override fun showFilesLoadedAlert() {
        Alert(Alert.AlertType.INFORMATION).apply {
            title = "Files Loaded"
            headerText = null
            contentText = "All Files loaded."
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