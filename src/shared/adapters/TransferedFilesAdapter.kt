package shared.adapters

import javafx.scene.control.Label
import javafx.scene.layout.VBox

class TransferedFilesAdapter: VBox() {
    private var files = mutableListOf<ListItem>()

    private fun updateList() {
        children.clear()
        files.forEach {
            children.add(it)
        }
        styleClass.add("fs-files-list")
    }

    fun submitList(files: List<String>) {
        this.files.clear()
        files.forEach {
            this.files.add(ListItem(it).apply { styleClass.add("fs-list-item") })
        }
        updateList()
    }

    fun setDownloading(pos: Int) {
        files[pos].changeStatusToDownloading()
    }

    fun setConcluded(pos: Int) {
        files[pos].changeStatusToConcluded()
    }

    class ListItem constructor(private val file: String): Label() {
        init {
            text = "$file -> Waiting"
        }

        fun changeStatusToDownloading() {
            text = "$file -> Downloading"
        }

        fun changeStatusToConcluded() {
            text = "$file -> Concluded"
        }
    }

}