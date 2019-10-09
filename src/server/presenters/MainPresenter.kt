package server.presenters

import javafx.stage.Window
import server.contracts.IMainContract
import server.db.Entry
import server.db.FilesRepository
import server.ui.MainScene
import server.ui.MainStage
import java.util.*

class MainPresenter constructor(private val owner: Window): Observer, IMainContract.IPresenter {
    override val view = MainScene()
    private val repository: FilesRepository = FilesRepository.get()

    init {
        repository.addObserver(this)
    }

    override fun setListeners() {

    }

    override fun update(o: Observable?, arg: Any?) {
        val entries = repository.allEntries()
        view.addEntries(entries)
    }
}
