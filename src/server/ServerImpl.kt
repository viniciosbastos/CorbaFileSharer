package server


import server.db.Entry
import server.db.FilesRepository
import shared.FileSharer.ServerPOA

class ServerImpl: ServerPOA() {
    override fun updateRemoteFiles(clientUsername: String?, files: Array<out String>?) {
        FilesRepository.get().addEntry(Entry(clientUsername!!, files!!.toList()))
    }

    override fun getFilesFromRemote(clientUsername: String?, search: String): Array<String> {
        val list = FilesRepository.get().getAllEntriesExceptFromUser(clientUsername!!, search)
        return list.toTypedArray()
    }

    override fun getOwnerOfFile(fileName: String?): String {
        return FilesRepository.get().getFileOwnerUsername(fileName!!)
    }
}