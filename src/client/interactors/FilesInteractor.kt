package client.interactors

import client.UserPreferences
import client.services.P2PClientService
import client.services.ServerService
import shared.FileSharer.Server
import java.io.File
import java.io.FileOutputStream

class FilesInteractor: IRemoteFilesInteractor {
    private val server: Server = ServerService().init()

    override fun updateRemote() {
        val userPreferences = UserPreferences.get()
        val sharedFolder = userPreferences.sharedFolder
        val files = File(sharedFolder).list()
        server.updateRemoteFiles(userPreferences.username, files)
    }

    override fun updateLocal(): Array<String> {
        return server.getFilesFromRemote(UserPreferences.get().username)
    }

    override fun downloadFile(fileName: String) {
        val owner = server.getOwnerOfFile(fileName)
        val downloadedFile: ByteArray = P2PClientService().init(owner).downloadFile(fileName)

        val fos = FileOutputStream(File(UserPreferences.get().sharedFolder + File.separator + fileName))
        fos.write(downloadedFile)
        fos.close()
    }
}
