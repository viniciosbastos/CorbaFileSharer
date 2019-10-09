package server

import shared.FileSharerServer.FileSharerPOA


class FileSharerImpl: FileSharerPOA() {
    override fun updateRemoteFiles(clientUsername: String?, files: Array<out String>?) {
        println("Cheguei aqui")
    }

    override fun getFilesFromRemote(): Array<String> {
        println("Cheguei aqui")
        return arrayOf("AAAA", "BBBB")
    }
}