package shared.adapters

import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import server.db.Entry

class EntryAdapter: VBox() {
    private var entries: List<Entry> = mutableListOf()

    private fun initList() {
        children.clear()
        entries.forEach {
            children.add(View(it).pane)
        }
    }

    fun submitList(entries: List<Entry>) {
        this.entries = entries
        initList()
    }

    class View constructor(private val entry: Entry){
        private val _root = VBox()
        private val _filesList = VBox().apply {
            styleClass.add("fs-hidden")
        }

        init {
            _root.children.add(_filesList)
        }

        val pane: Pane
            get() = bindData()

        private fun bindData(): Pane {
            _root.children.add(0, Label(entry.clientUsername).apply {
                styleClass.addAll("fs-list-title", "fs-label-padding")
                setOnMouseClicked { defaultClickAction() }
            } )
            entry.filesList.forEach {
                _filesList.children.add(Label(it).apply {
                    styleClass.addAll("fs-list-item", "fs-label-padding")
                })
            }
            return _root
        }

        private fun defaultClickAction()  {
            if (_filesList.styleClass.contains("fs-hidden")) _filesList.styleClass.remove("fs-hidden")
            else _filesList.styleClass.add("fs-hidden")
        }

    }
}