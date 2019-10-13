package server


import server.db.Entry
import server.db.FilesRepository
import shared.FileSharerServer.FileSharerPOA


class FileSharerImpl: FileSharerPOA() {
    override fun updateRemoteFiles(clientUsername: String?, files: Array<out String>?) {
        FilesRepository.get().addEntry(Entry(clientUsername!!, files!!.toList()))
    }

    override fun getFilesFromRemote(clientUsername: String?): Array<String> {
        val list = FilesRepository.get().getAllEntriesExceptFromUser(clientUsername!!)
        return list.toTypedArray()
    }

    override fun getOwnerOfFile(fileName: String?): String {
        return FilesRepository.get().getFileOwnerUsername(fileName!!)
    }
}