package server.db

import java.util.*

class FilesRepository private constructor(): Observable() {
    private val clients: MutableList<Entry> = mutableListOf()

    fun addEntry(entry: Entry) {
        clients.add(entry)
        setChanged()
        notifyObservers()
    }

    fun getAllEntries(): List<Entry> {
        return clients.toList()
    }

    fun getAllEntriesExceptFromUser(username: String): List<String> {
        return clients.filter { it.clientUsername != username }.flatMap { it.filesList }
    }

    fun getFileOwnerUsername(fileName: String): String {
        val entry = clients.find { it.filesList.contains(fileName) }
        return entry?.clientUsername ?: ""
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