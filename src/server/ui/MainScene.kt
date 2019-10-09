package server.ui

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.RowConstraints
import server.contracts.IMainContract
import server.db.Entry

open class MainScene: IMainContract.IScene {
    private lateinit var rootElement: GridPane
    private lateinit var listPane: ScrollPane
    private lateinit var listView: ListView<Pane>
    private lateinit var entriesList: ObservableList<Pane>

    lateinit var titleLabel: Label

    private fun createRootElement() {
        rootElement = GridPane().apply {
            rowConstraints.addAll(RowConstraints(50.0), RowConstraints(550.0))
        }
    }

    private fun addHeader() {
        val box = HBox()
        titleLabel = Label("FileSharer")
        box.children.addAll(titleLabel)
        rootElement.add(box, 0, 0)
    }

    private fun addListPane() {
        listView = ListView()
        entriesList = FXCollections.observableArrayList()
        listView.items = entriesList

        listPane = ScrollPane().apply {
            content = listView
            vbarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
        }
        rootElement.add(listPane, 0, 1)
    }

    override fun render(): Scene {
        createRootElement()
        addHeader()
        addListPane()
        return Scene(rootElement, 1200.0, 600.0).apply { stylesheets.add("server/ui/styles/main.css") }
    }

    private fun createClientPane(entry: Entry) {
        val pane = Pane()
        pane.children.add(Label(entry.clientUsername))
        entriesList.add(pane)
    }

    override fun addClient(entry: Entry) {
        println("OLHA EU AQUI")
    }

    override fun showClientsFiles(index: Int) {

    }

    override fun addEntries(entries: List<Entry>) {
        entries.forEach { createClientPane(it) }
    }
}