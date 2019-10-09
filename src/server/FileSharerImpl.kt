package server


import server.db.Entry
import server.db.FilesRepository
import shared.FileSharerServer.FileSharerPOA


class FileSharerImpl: FileSharerPOA() {
    override fun updateRemoteFiles(clientUsername: String?, files: Array<out String>?) {
        FilesRepository.get().addEntry(Entry(clientUsername!!, files!!.toList()))
    }

    override fun getFilesFromRemote(): Array<String> {
        println("Cheguei aqui")
        return arrayOf("AAAA", "BBBB")
    }
}