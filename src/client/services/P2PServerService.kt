package client.services

import client.P2PServerImpl
import client.UserPreferences
import org.omg.CosNaming.NameComponent

class P2PServerService private constructor(){
    private val cfg = BaseCorbaCfg.get()
    private var p2pServerName: String? = null

    fun init() {
        p2pServerName?.let { cfg.naming.unbind(arrayOf(NameComponent("FileSharerP2P_$it", "FileSharerP2P"))) }
        p2pServerName = UserPreferences.get().username
        cfg.naming.rebind(arrayOf(NameComponent("FileSharerP2P_$p2pServerName", "FileSharerP2P")), cfg.rootPoa.servant_to_reference(
            P2PServerImpl()
        ))
        cfg.rootPoa.the_POAManager().activate()
    }

    companion object {
        private var INSTANCE: P2PServerService? = null

        fun get(): P2PServerService {
            if (INSTANCE == null)
                INSTANCE = P2PServerService()
            return INSTANCE!!
        }
    }
}