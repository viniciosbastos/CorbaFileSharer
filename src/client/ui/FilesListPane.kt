package client.ui

import javafx.application.Platform
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import shared.adapters.FilesAdapter

class FilesListPane constructor(headerText: String, buttonName: String, private val listItemOnClick: (Label) -> Unit = {}): VBox() {

    private val scrollPane = ScrollPane().apply { styleClass.add("fs-files-scroll-view") }
    private val adapter = FilesAdapter(listItemOnClick)
    private val reloadButton = Button(buttonName).apply { styleClass.addAll("fs-custom-button", "fs-infinity-width") }

    init {
        scrollPane.apply {
            content = adapter
            vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
            hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        }
        styleClass.add("fs-files-list-container")
        val headerBox = HBox()
        headerBox.children.add(Label(headerText))
        children.addAll(headerBox, reloadButton, scrollPane)
    }

    fun setFilesList(filesList: List<String>) {
        Platform.runLater { adapter.submitList(filesList) }
    }

    fun getReloadButton() = reloadButton
}