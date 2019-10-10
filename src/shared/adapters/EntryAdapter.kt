package shared.adapters

import javafx.collections.FXCollections
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import server.db.Entry

class EntryAdapter constructor(private val entry: Entry) {
    private val _root = Pane()
    private val filesListView = ListView<String>()
    private val filesList = FXCollections.observableArrayList<String>(entry.filesList)

    init {
        filesListView.items = filesList
    }

    val pane: Pane
        get() = createPane()

    private fun createPane(): Pane {
        val box = VBox()
        box.children.addAll(Label(entry.clientUsername), filesListView)
        return _root.apply { children.add(box) }
    }
}