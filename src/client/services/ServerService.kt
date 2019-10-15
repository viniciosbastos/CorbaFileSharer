package client.services

import org.omg.CosNaming.NameComponent
import shared.FileSharer.Server
import shared.FileSharer.ServerHelper


class ServerService {
    private val cfg = BaseCorbaCfg.get()

    fun init(): Server {
        val obj = cfg.naming.resolve(arrayOf(NameComponent("FileSharer", "FileSharerServer")))
        return ServerHelper.narrow(obj)
    }
}