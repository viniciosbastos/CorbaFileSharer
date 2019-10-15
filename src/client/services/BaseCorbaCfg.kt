package client.services

import org.omg.CORBA.ORB
import org.omg.CosNaming.NamingContext
import org.omg.CosNaming.NamingContextHelper
import org.omg.PortableServer.POA
import org.omg.PortableServer.POAHelper

class BaseCorbaCfg private constructor(args: Array<String>) {
    val orb: ORB
    val rootPoa: POA
    val naming: NamingContext

    init {
        orb = ORB.init(args, null)
        rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"))
        naming = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"))
    }

    companion object {
        var INSTANCE: BaseCorbaCfg? = null

        fun create(args: Array<String>) {
            if (INSTANCE == null)
                INSTANCE = BaseCorbaCfg(args)
        }

        fun get(): BaseCorbaCfg {
            return INSTANCE!!
        }
    }
}