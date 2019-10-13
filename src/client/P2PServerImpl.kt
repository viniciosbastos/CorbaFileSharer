package client

import shared.FileSharer.P2PPOA
import java.io.File
import java.io.FileInputStream

class P2PServerImpl: P2PPOA() {
    override fun downloadFile(fileName: String?): ByteArray {
        val sharedFolder = UserPreferences.get().sharedFolder
        val file = File(sharedFolder + File.separator + fileName)
        val byteArray = ByteArray(file.length().toInt())
        val fis = FileInputStream(file)
        fis.read(byteArray)
        fis.close()
        return byteArray
    }
}