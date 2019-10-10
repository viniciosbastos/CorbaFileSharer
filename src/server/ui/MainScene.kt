package server.ui

import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.layout.*
import server.contracts.IMainContract
import server.db.Entry
import shared.adapters.EntryAdapter

open class MainScene: IMainContract.IScene {
    private lateinit var rootElement: GridPane
    private lateinit var scrollPane: ScrollPane
    private lateinit var clientsList: VBox
    private val entryAdapter = EntryAdapter().apply { styleClass.add("fs-list") }

    lateinit var titleLabel: Label

    private fun createRootElement() {
        rootElement = GridPane().apply {
            rowConstraints.addAll(RowConstraints(50.0), RowConstraints(550.0))
        }
    }

    private fun addHeader() {
        val box = HBox()
        titleLabel = Label("FileSharer").apply { styleClass.add("fs-title") }
        box.children.addAll(titleLabel)
        rootElement.add(box, 0, 0)
    }

    private fun addClientListPane() {
        clientsList = VBox().apply { styleClass.add("fs-list") }
        scrollPane = ScrollPane().apply {
            content = entryAdapter
            vbarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
            hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        }
        rootElement.add(scrollPane, 0, 1)
    }

    override fun render(): Scene {
        createRootElement()
        addHeader()
        addClientListPane()
        return Scene(rootElement, 1200.0, 600.0).apply { stylesheets.add("server/ui/styles/main.css") }
    }

    override fun showClientsFiles(index: Int) {
        TODO("Open sublist to each client when name is clicked in list")
    }

    override fun addEntries(entries: List<Entry>) {
        Platform.runLater { entryAdapter.submitList(entries) }
    }

    override fun addEntry(entry: Entry) {
        TODO("Add Single Entry")
    }
}