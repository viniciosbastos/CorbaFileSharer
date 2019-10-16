package client.ui

import client.contracts.IFilesContract
import javafx.scene.control.*
import javafx.scene.layout.*

class FilesPane: VBox(), IFilesContract.IView{
    override val root: Pane = Pane()

    private val downloadButton = Button("Download").apply { styleClass.add("fs-custom-button") }
    private val searchButton = Button("Search").apply { styleClass.add("fs-custom-button") }

    private val searchField = TextField().apply {
        promptText = "Name/Extension to search"
    }
    private val selectedFiles = mutableListOf<Label>()

    private val filesGrid = GridPane().apply { styleClass.add("fs-padding") }
    private val myFilesList = FilesListPane("My Files")
    private val listItemClicked = {item: Label -> onListItemClicked(item)}
    private val serverFilesList = FilesListPane("Server Files", listItemClicked)
    private val transferFilePane = TransferedFilesPane()

    init {
        filesGrid.apply {
            columnConstraints.addAll(ColumnConstraints(590.0), ColumnConstraints(590.0))
            add(myFilesList, 0, 0)
            add(serverFilesList, 1, 0)
            add(transferFilePane, 0, 1,2, 1)
        }
        val header = BorderPane().apply {
            left = addSearchBox()
            right = addDownloadButton()
        }
        children.addAll(header, filesGrid)
    }

    private fun addSearchBox(): HBox {
        return HBox().apply {
            children.addAll(searchField, searchButton)
            styleClass.add("fs-search-box")
        }
    }

    private fun addDownloadButton(): HBox {
        return HBox().apply {
            children.addAll(downloadButton)
            styleClass.add("fs-button-box")
        }
    }

    override fun render(): Pane {
        return root
    }

    override fun showTransferFiles() {
        transferFilePane.updateList(selectedFiles.map { it.text })
    }

    override fun showMyFilesList(files: Array<String>) {
        myFilesList.setFilesList(files.toList())
    }

    override fun showServerFilesList(files: Array<String>) {
        serverFilesList.setFilesList(files.toList())
    }

    private fun onListItemClicked(listItem: Label) {
        if (selectedFiles.contains(listItem)) {
            listItem.styleClass.remove("fs-active")
            selectedFiles.remove(listItem)
        }
        else {
            listItem.styleClass.add("fs-active")
            selectedFiles.add(listItem)
        }

    }

    override fun clearSelectedFiles() {
        selectedFiles.forEach {
            it.styleClass.remove("fs-active")
        }
        selectedFiles.clear()
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

    override fun showDownloadingFile(pos: Int) {
        transferFilePane.setDownloading(pos)
    }

    override fun showConcludedFile(pos: Int) {
        transferFilePane.setConcluded(pos)
    }

    fun getReloadServerButton() = serverFilesList.getReloadButton()
    fun getReloadLocalButton() = myFilesList.getReloadButton()
    fun getDownloadButton() = downloadButton
    fun getSearchButton() = searchButton
    fun getSearchFieldText() = searchField.text.trim()
    fun getSelectedFiles() = selectedFiles.map { it.text }
}