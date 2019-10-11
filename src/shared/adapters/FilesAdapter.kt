package shared.adapters

import javafx.scene.control.Label
import javafx.scene.layout.VBox

class FilesAdapter: VBox() {
    private var files: List<String> = mutableListOf()

    private fun updateList() {
        children.clear()
        files.forEach {
            children.add(ListItem(it).apply { styleClass.add("fs-list-item") })
        }
    }

    fun submitList(files: List<String>) {
        this.files = files
        updateList()
    }

    class ListItem constructor(file: String): Label() {
        init {
            text = file
        }
    }
}