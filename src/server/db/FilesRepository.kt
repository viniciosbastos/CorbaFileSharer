package server.db

import java.util.*

class FilesRepository private constructor(): Observable() {
    private val clients: MutableList<Entry> = mutableListOf()

    fun addEntry(entry: Entry) {
        clients.add(entry)
        setChanged()
        notifyObservers()
    }

    fun allEntries(): List<Entry> {
        return clients.toList()
    }

    fun entriesExceptUsername(username: String): List<String> {
        return clients.filter { it.clientUsername != username }.flatMap { it.filesList }
    }

    companion object {
        private var INSTANCE: FilesRepository? = null

        fun get(): FilesRepository {
            if (INSTANCE == null)
                INSTANCE = FilesRepository()
            return INSTANCE!!
        }
    }
}