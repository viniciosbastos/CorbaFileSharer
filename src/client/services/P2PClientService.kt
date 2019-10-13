package client.services

import org.omg.CosNaming.NameComponent
import shared.FileSharer.P2P
import shared.FileSharer.P2PHelper

class P2PClientService {
    private val cfg = BaseCorbaCfg

    fun init(clientName: String): P2P {
        val obj = cfg.naming.resolve(arrayOf(NameComponent("FileSharerP2P_$clientName", "FileSharerP2P")))
        return P2PHelper.narrow(obj)
    }
}