package client.interactors

import client.UserPreferences
import org.omg.CORBA.ORB
import org.omg.CosNaming.NameComponent
import org.omg.CosNaming.NamingContextHelper
import shared.FileSharerServer.FileSharer
import shared.FileSharerServer.FileSharerHelper
import java.io.File

class RemoteFilesCorbaInteractor: IRemoteFilesInteractor {
    private lateinit var fileSharer: FileSharer
    init {
        init()
    }

    private fun init() {
        val orb: ORB = ORB.init(arrayOf("-ORBInitialPort", "9999", "-ORBInitialHost", "localhost"), null)
        val naming = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"))

        val obj = naming.resolve(arrayOf(NameComponent("FileSharer", "FileSharerServer")))
        fileSharer = FileSharerHelper.narrow(obj)
    }

    override fun updateRemote() {
        val userPreferences = UserPreferences.get()
        val sharedFolder = userPreferences.sharedFolder
        val files = File(sharedFolder).list()
        fileSharer.updateRemoteFiles(userPreferences.username, files)
    }

    override fun updateLocal(): Array<String> {
        return fileSharer.getFilesFromRemote(UserPreferences.get().username)
    }

    override fun downloadFile(fileName: String) {
        val owner = fileSharer.getOwnerOfFile(fileName)
        println("Owner: $owner")
    }
}
