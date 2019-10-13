package shared.adapters

import javafx.scene.control.Label
import javafx.scene.layout.VBox

class FilesAdapter(private val listItemOnClick: (Label) -> Unit): VBox() {
    private var files: List<String> = mutableListOf()

    private fun updateList() {
        children.clear()
        files.forEach {
            children.add(ListItem(it, listItemOnClick).apply { styleClass.add("fs-list-item") })
        }
    }

    fun submitList(files: List<String>) {
        this.files = files
        updateList()
    }

    class ListItem constructor(file: String, listItemOnClick: (Label) -> Unit): Label() {
        init {
            text = file
            setOnMouseClicked { listItemOnClick(this) }
        }
    }
}