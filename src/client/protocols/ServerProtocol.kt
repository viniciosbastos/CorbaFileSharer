package client.protocols

import corba.IBaseProtocol
import org.omg.CORBA.ORB
import org.omg.CosNaming.NameComponent
import org.omg.CosNaming.NamingContextHelper
import shared.FileSharerServer.FileSharer
import shared.FileSharerServer.FileSharerHelper

class ServerProtocol: IBaseProtocol {
    private lateinit var fileSharer: FileSharer

    init {
        init()
    }

    override fun init() {
        val orb: ORB = ORB.init(arrayOf("-ORBInitialPort", "9999", "-ORBInitialHost", "localhost"), null)
        val naming = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"))

        val obj = naming.resolve(arrayOf(NameComponent("FileSharer", "FileSharerServer")))
        fileSharer = FileSharerHelper.narrow(obj)

//        println("5+3=${calc.soma(5F, 3F)}")
//        println("10/2=${calc.divisao(10F, 2F)}")
    }

    fun synchronize() {
        fileSharer.updateRemoteFiles(arrayOf("AAAA", "BBBB", "CCCC"))
        println(fileSharer.filesFromRemote)
    }



    companion object {
        private var INSTANCE: ServerProtocol? = null

        fun get(): ServerProtocol {
            if (INSTANCE == null) {
                INSTANCE = ServerProtocol()
            }
            return INSTANCE!!
        }
    }
}