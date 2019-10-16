package client.ui

import javafx.application.Platform
import javafx.scene.control.ScrollPane
import javafx.scene.layout.VBox
import shared.adapters.TransferedFilesAdapter

class TransferedFilesPane: VBox() {

    private val scrollPane = ScrollPane().apply { styleClass.add("fs-transf-files-scroll-view") }
    private val adapter = TransferedFilesAdapter()

    init {
        scrollPane.apply {
            content = adapter
            vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
            hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        }
        children.add(scrollPane)
    }

    fun updateList(list: List<String>) {
        Platform.runLater { adapter.submitList(list) }
    }

    fun setDownloading(pos: Int) {
        Platform.runLater { adapter.setDownloading(pos) }
    }

    fun setConcluded(pos: Int) {
        Platform.runLater { adapter.setConcluded(pos) }
    }

}